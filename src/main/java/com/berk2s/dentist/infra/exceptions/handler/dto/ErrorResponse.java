package com.berk2s.dentist.infra.exceptions.handler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponse {
    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("code")
    private String code;

    @JsonProperty("details")
    private List<String> details;
}
