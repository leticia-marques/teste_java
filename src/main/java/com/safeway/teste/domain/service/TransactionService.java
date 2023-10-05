package com.safeway.teste.domain.service;

import com.safeway.teste.domain.dto.notification.Message;
import com.safeway.teste.domain.dto.transaction.TransactionInputDto;
import com.safeway.teste.domain.dto.transaction.TransactionListDto;
import com.safeway.teste.domain.dto.transaction.TransactionResponseDto;
import com.safeway.teste.domain.enumarated.TransactionType;
import com.safeway.teste.domain.exception.BusinessException;
import com.safeway.teste.domain.exception.TransactionNotFoundException;
import com.safeway.teste.domain.model.Client;
import com.safeway.teste.domain.model.Company;
import com.safeway.teste.domain.model.Transaction;
import com.safeway.teste.domain.repository.TransactionRepository;
import com.safeway.teste.domain.service.notification.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private static final BigDecimal TRANSACTION_FEE = new BigDecimal("0.05");
    private CompanyService companyService;

    private ClientService clientService;

    private TransactionRepository transactionRepository;

    private List<NotificationService> notificationServices;

    @Autowired
    public TransactionService(CompanyService companyService, ClientService clientService,
                              TransactionRepository transactionRepository, List<NotificationService> notificationServices) {
        this.companyService = companyService;
        this.clientService = clientService;
        this.transactionRepository = transactionRepository;
        this.notificationServices =  notificationServices;
    }


    @Transactional
    public TransactionResponseDto deposit(TransactionInputDto transactionDto) {
        Company company = this.companyService.getById(new Company(transactionDto.companyId()));
        Client client =  this.clientService.getById(new Client(transactionDto.clientId()));

        Transaction transaction = new Transaction(client, company, transactionDto.value(), TransactionType.DEPOSIT);
        transaction = this.discountFee(transaction);
        transaction = this.transactionRepository.save(transaction);
        for (NotificationService notification: this.notificationServices) {
            notification.notification(new Message("deposito", transactionDto.value(), company, client));
        }
        return new TransactionResponseDto(transaction);
    }

    public List<Transaction> listAll() {
        return this.transactionRepository.findAll();
    }

    public TransactionResponseDto getById(Long id) {
        Transaction transaction = this.getById(new Transaction(id));
        return new TransactionResponseDto(transaction);
    }

    public Transaction getById(Transaction transaction) {
        return this.transactionRepository.findById(transaction.getId()).orElseThrow(() ->
                new TransactionNotFoundException(transaction.getId()));
    }

    @Transactional
    public void delete(Long transactionId) {
        Transaction transaction = this.getById(new Transaction(transactionId));
        this.transactionRepository.delete(transaction);
    }

    @Transactional
    public TransactionResponseDto withdraw(TransactionInputDto transactionDto) {
        Company company = this.companyService.getById(new Company(transactionDto.companyId()));
        Client client =  this.clientService.getById(new Client(transactionDto.clientId()));
        Optional<Transaction> lastTransaction  = this.getLastTransactionByClientId(transactionDto.clientId(), transactionDto.companyId());
        BigDecimal valueFee = transactionDto.value().multiply(TRANSACTION_FEE);

        BigDecimal totalValueWithfee = transactionDto.value().subtract(valueFee);
        if (lastTransaction.isPresent()) {
            if (transactionDto.value().add(transactionDto.value().multiply(TRANSACTION_FEE)).compareTo(lastTransaction.get().getTotalBalance()) < 1 ) {
                company.subtractBalance(totalValueWithfee);

                Transaction newTransaction = new Transaction(client, company, transactionDto.value(), TransactionType.WITHDRAW);
                newTransaction.setTotalBalance(lastTransaction.get().getTotalBalance());
                newTransaction.withdrawWithFee(transactionDto.value().add(valueFee));
                newTransaction = this.transactionRepository.save(newTransaction);
                for (NotificationService notification: this.notificationServices) {
                    notification.notification(new Message("saque", transactionDto.value(), company, client));
                }
                return new TransactionResponseDto(newTransaction);
            }
        }
        throw new BusinessException("Not enough balance");
    }

    public Page<TransactionListDto> search(Pageable pageable) {
        Page<Transaction> page = this.transactionRepository.findAll(pageable);
        return page.map(transaction -> new TransactionListDto(transaction));
    }


    public Optional<Transaction> getLastTransactionByClientId(Long clientId, Long companyId) {
        return this.transactionRepository.findLastTransactionForClientId(clientId, companyId);
    }

    @Transactional
    public Transaction discountFee(Transaction transaction) {
        Optional<Transaction> lastTransaction  = this.getLastTransactionByClientId(transaction.getClient().getId(), transaction.getCompany().getId());
        BigDecimal depositValue = transaction.getValue();
        BigDecimal depositValueWithFee = depositValue.subtract(depositValue.multiply(TRANSACTION_FEE));
        transaction.getCompany().addBalance(depositValue);
        transaction.getClient().addCompany(transaction.getCompany());
        if (lastTransaction.isPresent()){
            transaction.addTotalBalance(lastTransaction.get().getTotalBalance());
        }
        transaction.subtractFee(TRANSACTION_FEE);
        transaction.addTotalBalance(depositValueWithFee );

        return transaction;
    }
}
