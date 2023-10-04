package com.safeway.teste.domain.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyInputDto(

        @NotBlank
        @CNPJ
        String cnpj,

        @NotBlank
        String name
) {
}
