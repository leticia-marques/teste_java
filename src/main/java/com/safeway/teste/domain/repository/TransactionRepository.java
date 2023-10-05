package com.safeway.teste.domain.repository;

import com.safeway.teste.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.client.id = :clientId " +
            "AND t.company.id = :companyId " +
            "ORDER BY t.id DESC LIMIT 1")
    Optional<Transaction> findLastTransactionForClientId(Long clientId, Long companyId);
}
