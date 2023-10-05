package com.safeway.teste.domain.dto.company;

import com.safeway.teste.domain.dto.transaction.TransactionResponseDto;
import com.safeway.teste.domain.model.Company;
import com.safeway.teste.domain.model.Transaction;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record CompanyResponseDto(

        Long id,
        String name,

        List <TransactionResponseDto> transactions,

        BigDecimal balance
) {
    public CompanyResponseDto(Company company) {
        this(company.getId(), company.getName(), TransactionResponseDto.convertList(company.getTransactions()),
                company.getBalance());
    }

    public static List<CompanyResponseDto> convertList(Collection<Company> listCompanies){
        return listCompanies.stream().map(CompanyResponseDto::new).collect(Collectors.toList());
    }

}

