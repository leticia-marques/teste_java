package com.safeway.teste.domain.dto.client;

import com.safeway.teste.domain.dto.company.CompanyResponseDto;
import com.safeway.teste.domain.model.Client;
import com.safeway.teste.domain.model.Company;

import java.util.List;
import java.util.Set;

public record ClientResponseDto(
        Long id,

        String name,

        List <CompanyResponseDto> companies
) {
    public ClientResponseDto(Client client) {
        this(client.getId(), client.getName(), CompanyResponseDto.convertList(client.getCompanies()));
    }


}
