package com.safeway.teste.domain.dto.transaction;

import com.safeway.teste.domain.enumarated.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionInputDto(
    @NotNull
    Long clientId,

    @NotNull
    Long companyId,

    @NotNull
    @Positive
    BigDecimal value

) {
}
