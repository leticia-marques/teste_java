package com.safeway.teste.domain.exception;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Long clientId, Long companyId) {
        super(String.format("Transaction not found for client %d and company $d", clientId, companyId));
    }
}
