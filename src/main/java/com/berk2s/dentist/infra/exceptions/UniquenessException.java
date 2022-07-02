package com.berk2s.dentist.infra.exceptions;

public class UniquenessException extends RuntimeException {
    public UniquenessException(String message) {
        super(message);
    }
}
