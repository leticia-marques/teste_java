package com.safeway.teste.api.controller;

import com.safeway.teste.domain.dto.company.CompanyInputDto;
import com.safeway.teste.domain.dto.company.CompanyListDto;
import com.safeway.teste.domain.dto.company.CompanyResponseDto;
import com.safeway.teste.domain.model.Company;
import com.safeway.teste.domain.repository.CompanyRepository;
import com.safeway.teste.domain.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private CompanyRepository companyRepository;
    @Autowired

    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/lista")
    public List<Company> listAll() {
        return  this.companyRepository.findAll();
    }

    @PostMapping
    public CompanyResponseDto save(@RequestBody @Valid CompanyInputDto companyDto) {
        return this.companyService.save(companyDto);
    }

    @GetMapping
    public Page<CompanyListDto> search(Pageable page) {
        return this.companyService.search(page);
    }

    @GetMapping("/{companyId}")
    public CompanyResponseDto getById(@PathVariable Long companyId) {
        return this.companyService.getById(companyId);
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long companyId) {
        this.companyService.delete(companyId);
    }
}
