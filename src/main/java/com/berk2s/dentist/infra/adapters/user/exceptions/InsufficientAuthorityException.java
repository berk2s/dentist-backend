package com.berk2s.dentist.infra.adapters.user.exceptions;

public class InsufficientAuthorityException extends RuntimeException {
    public InsufficientAuthorityException(String message) {
        super(message);
    }
}
