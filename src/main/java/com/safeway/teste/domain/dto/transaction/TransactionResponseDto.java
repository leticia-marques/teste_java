package com.safeway.teste.domain.dto.transaction;

import com.safeway.teste.domain.enumarated.TransactionType;
import com.safeway.teste.domain.model.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionResponseDto(

        @NotNull
        Long transactionId,

        @NotNull
        Long clientId,

        @NotNull
        Long companyId,

        @NotNull
        @Positive
        BigDecimal value,

        @NotNull
        TransactionType transactionType
) {
    public TransactionResponseDto(Transaction transaction) {
        this(transaction.getId(),transaction.getClient().getId(), transaction.getCompany().getId(),
                transaction.getValue(), transaction.getTransactionType());
    }
}
