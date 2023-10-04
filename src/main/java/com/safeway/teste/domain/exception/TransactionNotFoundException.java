package com.safeway.teste.domain.exception;

public class TransactionNotFoundException extends EntityNotFoundException{
    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotFoundException(Long companyId) {
        super(String.format("Transaction with id %d not found.", companyId));
    }
}
