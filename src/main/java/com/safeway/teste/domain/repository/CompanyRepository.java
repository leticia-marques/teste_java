package com.safeway.teste.domain.repository;

import com.safeway.teste.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
