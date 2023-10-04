package com.safeway.teste.domain.model;

import com.safeway.teste.domain.dto.client.ClientInputDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CPF
    private String cpf;



    public Client(ClientInputDto clientDto) {
        this.cpf = clientDto.cpf();
        this.name = clientDto.name();
    }

    public Client(Long clientId) {
        this.id = clientId;
    }
}
