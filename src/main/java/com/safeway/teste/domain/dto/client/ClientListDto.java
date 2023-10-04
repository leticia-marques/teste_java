package com.safeway.teste.domain.dto.client;

import com.safeway.teste.domain.model.Client;

public record ClientListDto(

        Long id,

        String name

//       BigDecimal balance
) {
    public ClientListDto(Client client) {
        this(client.getId(), client.getName());
    }
}
