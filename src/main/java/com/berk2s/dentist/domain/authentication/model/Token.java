package com.berk2s.dentist.domain.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Token {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresIn;
    private List<String> scopes;

}
