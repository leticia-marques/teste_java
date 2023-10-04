package com.safeway.teste.api.controller;

import com.safeway.teste.domain.dto.transaction.TransactionInputDto;
import com.safeway.teste.domain.dto.transaction.TransactionListDto;
import com.safeway.teste.domain.dto.transaction.TransactionResponseDto;
import com.safeway.teste.domain.model.Transaction;
import com.safeway.teste.domain.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping("/withdraw")
    public TransactionResponseDto withdraw(@RequestBody @Valid TransactionInputDto transactionDto) {
        return this.transactionService.withdraw(transactionDto);
    }

    @GetMapping("/listall")
    public List<Transaction> listAll() {
        return this.transactionService.listAll();
    }

    @GetMapping
    public Page<TransactionListDto> search(Pageable page) {
        return this.transactionService.search(page);
    }
    @PostMapping("/deposit")
    public TransactionResponseDto deposit(@RequestBody @Valid TransactionInputDto transactionDto) {
        return this.transactionService.deposit(transactionDto);
    }

    @GetMapping("/{transactionId}")
    public TransactionResponseDto getById(@PathVariable Long id) {
        return this.transactionService.getById(id);
    }

    @DeleteMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransactionById(@PathVariable Long transactionId) {
        this.transactionService.delete(transactionId);
    }


}
