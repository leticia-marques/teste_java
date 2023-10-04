package com.safeway.teste.domain.service;

import com.safeway.teste.domain.dto.client.ClientResponseDto;
import com.safeway.teste.domain.dto.company.CompanyInputDto;
import com.safeway.teste.domain.dto.company.CompanyListDto;
import com.safeway.teste.domain.dto.company.CompanyResponseDto;
import com.safeway.teste.domain.exceptions.CompanyNotFoundException;
import com.safeway.teste.domain.model.Company;
import com.safeway.teste.domain.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyResponseDto save(CompanyInputDto companyDto) {
        Company company = new Company(companyDto);
        company = this.companyRepository.save(company);
        return new CompanyResponseDto(company);
    }

    public Page<CompanyListDto> search(Pageable pageable) {
        Page<Company> page = this.companyRepository.findAll(pageable);
        return page.map(company -> new CompanyListDto(company));
    }

    public CompanyResponseDto getById(Long companyId) {
        Company company = this.getById(new Company(companyId));
        return new CompanyResponseDto(company);
    }

    public Company getById(Company company) {
        return this.companyRepository.findById(company.getId()).orElseThrow(() ->
                new CompanyNotFoundException(company.getId()));
    }

    @Transactional
    public void delete(Long companyId) {
        Company company = this.getById(new Company(companyId));
        this.companyRepository.delete(company);
    }
}
