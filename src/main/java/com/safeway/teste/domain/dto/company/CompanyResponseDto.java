package com.safeway.teste.domain.dto.company;

import com.safeway.teste.domain.model.Company;

public record CompanyResponseDto(

        Long id,
        String name
) {
    public CompanyResponseDto(Company company) {
        this(company.getId(), company.getName());
    }
}
