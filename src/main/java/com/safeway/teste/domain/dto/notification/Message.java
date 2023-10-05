package com.safeway.teste.domain.dto.notification;

import com.safeway.teste.domain.model.Client;
import com.safeway.teste.domain.model.Company;

import java.math.BigDecimal;

public record Message(
        String transactionType,

        BigDecimal transactionValue,

        Company company,

        Client client
) {
}
