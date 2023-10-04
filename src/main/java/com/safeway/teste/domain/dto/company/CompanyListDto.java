package com.safeway.teste.domain.dto.company;

import com.safeway.teste.domain.model.Company;

public record CompanyListDto(
        Long id,

        String Name
) {
    public CompanyListDto(Company company) {
        this(company.getId(), company.getName());
    }
}
