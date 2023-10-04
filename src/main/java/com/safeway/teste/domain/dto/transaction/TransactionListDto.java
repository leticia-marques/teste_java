package com.safeway.teste.domain.dto.transaction;

import com.safeway.teste.domain.model.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionListDto(
        @NotNull
        Long clientId,

        @NotNull
        Long companyId,

        @NotNull
        @Positive
        BigDecimal value
) {

    public TransactionListDto(Long clientId, Long companyId, BigDecimal value) {
        this.clientId = clientId;
        this.companyId = companyId;
        this.value = value;
    }

    public TransactionListDto(Transaction transaction) {
        this(transaction.getClient().getId(), transaction.getCompany().getId(), transaction.getValue());
    }
}
