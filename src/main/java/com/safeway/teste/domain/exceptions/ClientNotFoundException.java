package com.safeway.teste.domain.exceptions;

public class ClientNotFoundException extends EntityNotFoundException{
    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Long clientId) {
        super(String.format("Client with id %d not found.", clientId));
    }
}
