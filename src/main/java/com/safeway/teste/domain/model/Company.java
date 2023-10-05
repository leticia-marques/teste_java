package com.safeway.teste.domain.model;

import com.safeway.teste.domain.dto.company.CompanyInputDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CNPJ
    private String cnpj;

    private String name;

    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToMany(mappedBy = "companies")
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)

    private List<Transaction> transactions = new ArrayList<>();

    public Company(String cnpj, String name) {
        this.cnpj = cnpj;
        this.name = name;
    }

    public Company(CompanyInputDto companyDto) {
        this.cnpj = companyDto.cnpj();
        this.name = companyDto.name();
    }

    public Company(Long companyId) {
        this.id = companyId;
    }

    public void addBalance(BigDecimal totalValue) {
        BigDecimal total = this.balance.add(totalValue);
        this.balance = this.balance.add(total);
    }

    public void subtractBalance(BigDecimal totalValue){
        this.balance = this.balance.subtract(totalValue);
    }
}
