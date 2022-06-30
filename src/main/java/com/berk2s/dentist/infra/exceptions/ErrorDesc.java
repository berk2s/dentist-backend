package com.berk2s.dentist.infra.exceptions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ErrorDesc {
    USER_NOT_FOUND("User not found", "user.notFound"),
    INVALID_CREDENTIALS("Invalid credentials", "authentication.invalidCredentials"),
    SERVER_ERROR("Server error", "server.error"),
    INVALID_TOKEN("Invalid token", "authorization.invalidToken"),
    INVALID_REQUEST("Invalid request", "request.invalid"),
    INSUFFICIENT_AUTHORITY("Insufficient authority", "authentication.insufficientAuthority");

    private final String desc;
    private final String code;
    private static final Map<String, String> errorMap =  new HashMap<>();

    ErrorDesc(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    static {
        for (ErrorDesc errorDesc : ErrorDesc.values()) {
            errorMap.put(errorDesc.getDesc(), errorDesc.getCode());
        }
    }

    static public String getCodeFormDesc(String desc) {
        return errorMap.get(desc);
    }
}
