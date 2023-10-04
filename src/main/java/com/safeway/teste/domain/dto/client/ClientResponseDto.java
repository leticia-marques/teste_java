package com.safeway.teste.domain.dto.client;

import com.safeway.teste.domain.model.Client;
import com.safeway.teste.domain.model.Company;

public record ClientResponseDto(
        Long id,

        String name
) {
    public ClientResponseDto(Client client) {
        this(client.getId(), client.getName());
    }


}
