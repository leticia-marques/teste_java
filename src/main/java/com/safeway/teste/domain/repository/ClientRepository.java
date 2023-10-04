package com.safeway.teste.domain.repository;

import com.safeway.teste.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
