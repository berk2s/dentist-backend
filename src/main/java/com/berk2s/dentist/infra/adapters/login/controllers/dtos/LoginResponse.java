package com.berk2s.dentist.infra.adapters.login.controllers.dtos;

import com.berk2s.dentist.domain.authentication.model.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    public static LoginResponse fromModel(Token token) {
        return LoginResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .expiresIn((long) token.getExpiresIn().getMinute())
                .build();
    }

}

