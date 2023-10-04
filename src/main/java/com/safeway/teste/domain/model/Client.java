package com.safeway.teste.domain.model;

import com.safeway.teste.domain.dto.client.ClientInputDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CPF
    private String cpf;

    @ManyToMany
    @JoinTable(name = "client_companies",
    joinColumns = @JoinColumn(name = "clients_id"),
    inverseJoinColumns = @JoinColumn(name = "companies_id"))
    private Set<Company> companies = new HashSet<>();

    public Client(ClientInputDto clientDto) {
        this.cpf = clientDto.cpf();
        this.name = clientDto.name();
    }

    public Client(Long clientId) {
        this.id = clientId;
    }
}
