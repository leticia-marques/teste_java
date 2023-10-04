package com.safeway.teste.domain.exceptions;

public class CompanyNotFoundException extends EntityNotFoundException{

    public CompanyNotFoundException(String message) {
        super(message);
    }

    public CompanyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyNotFoundException(Long companyId) {
        super(String.format("Company with id %d not found.", companyId));
    }
}
