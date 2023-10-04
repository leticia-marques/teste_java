package com.safeway.teste.domain.service;

import com.safeway.teste.domain.dto.company.CompanyListDto;
import com.safeway.teste.domain.dto.transaction.TransactionInputDto;
import com.safeway.teste.domain.dto.transaction.TransactionListDto;
import com.safeway.teste.domain.dto.transaction.TransactionResponseDto;
import com.safeway.teste.domain.enumarated.TransactionType;
import com.safeway.teste.domain.exception.TransactionNotFoundException;
import com.safeway.teste.domain.model.Client;
import com.safeway.teste.domain.model.Company;
import com.safeway.teste.domain.model.Transaction;
import com.safeway.teste.domain.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private CompanyService companyService;

    private ClientService clientService;

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(CompanyService companyService, ClientService clientService, TransactionRepository transactionRepository) {
        this.companyService = companyService;
        this.clientService = clientService;
        this.transactionRepository = transactionRepository;
    }



    @Transactional
    public TransactionResponseDto deposit(TransactionInputDto transactionDto) {
        Company company = this.companyService.getById(new Company(transactionDto.companyId()));
        Client client =  this.clientService.getById(new Client(transactionDto.clientId()));

        Transaction transaction = new Transaction(client, company, transactionDto.value(), TransactionType.DEPOSIT);
        transaction = this.transactionRepository.save(transaction);
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

        Transaction transaction = new Transaction(client, company, transactionDto.value(), TransactionType.WITHDRAW);
        transaction = this.transactionRepository.save(transaction);
        return new TransactionResponseDto(transaction);
    }

    public Page<TransactionListDto> search(Pageable pageable) {
        Page<Transaction> page = this.transactionRepository.findAll(pageable);
        return page.map(transaction -> new TransactionListDto(transaction));
    }


}
