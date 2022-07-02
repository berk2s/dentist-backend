package com.berk2s.dentist.domain.role.exception;

public class RoleNameTakenException extends RuntimeException {
    public RoleNameTakenException(String message) {
        super(message);
    }
}
