package com.safeway.teste.domain.model;

import com.safeway.teste.domain.dto.company.CompanyInputDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CNPJ
    private String CNPJ;

    private String name;

    private BigDecimal balance = BigDecimal.ZERO;

    public Company(String CNPJ, String name) {
        this.CNPJ = CNPJ;
        this.name = name;
    }

    public Company(CompanyInputDto companyDto) {
        this.CNPJ = companyDto.CNPJ();
        this.name = companyDto.name();
    }

    public Company(Long companyId) {
        this.id = companyId;
    }
}
