package com.safeway.teste.domain.model;

import com.safeway.teste.domain.enumarated.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Company company;

    private BigDecimal value = BigDecimal.ZERO;

    private BigDecimal totalBalance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    public Transaction(Client client, Company company, BigDecimal value, TransactionType transactionType) {
        this.client = client;
        this.company = company;
        this.value = value;
        this.transactionType = transactionType;
    }

    public Transaction(Long id) {
        this.id = id;
    }

    public void subtractFee(BigDecimal fee) {
        BigDecimal taxFee = this.value.multiply(fee);
        this.value = this.value.subtract(taxFee);
        this.addTotalBalance(this.value);
    }

    public void addTotalBalance(BigDecimal balance) {
        this.totalBalance = this.totalBalance.add(balance);
    }
}
