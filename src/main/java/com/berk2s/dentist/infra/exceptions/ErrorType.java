package com.berk2s.dentist.infra.exceptions;

import lombok.Getter;

@Getter
public enum ErrorType {
    NOT_FOUND("not_found");

    final String type;

    ErrorType(String type) {
        this.type = type;
    }
}
