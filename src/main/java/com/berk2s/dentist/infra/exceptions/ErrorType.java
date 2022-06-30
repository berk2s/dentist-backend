package com.berk2s.dentist.infra.exceptions;

import lombok.Getter;

@Getter
public enum ErrorType {
    NOT_FOUND("not_found"),
    INVALID_REQUEST("invalid_request"),
    INVALID_GRANT("invalid_grant");

    final String type;

    ErrorType(String type) {
        this.type = type;
    }
}
