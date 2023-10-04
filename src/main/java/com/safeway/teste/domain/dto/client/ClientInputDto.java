package com.safeway.teste.domain.dto.client;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClientInputDto(

        @NotBlank
        String name,

        @NotBlank
        @CPF
        String cpf
) {
}
