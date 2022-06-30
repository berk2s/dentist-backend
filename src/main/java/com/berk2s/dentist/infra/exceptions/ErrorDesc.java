package com.berk2s.dentist.infra.exceptions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ErrorDesc {
    USER_NOT_FOUND("User not found", 1),
    INVALID_CREDENTIALS("Invalid credentials", 2);

    private final String desc;
    private final Integer code;
    private static final Map<String, Integer> errorMap =  new HashMap<>();

    ErrorDesc(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    static {
        for (ErrorDesc errorDesc : ErrorDesc.values()) {
            errorMap.put(errorDesc.getDesc(), errorDesc.getCode());
        }
    }

    static public Integer getCodeFormDesc(String desc) {
        return errorMap.get(desc);
    }
}
