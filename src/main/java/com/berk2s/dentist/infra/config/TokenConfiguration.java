package com.berk2s.dentist.infra.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("dentist-app.auth-config")
@Component
public class TokenConfiguration {

    private TokenDuration accessToken;

    private TokenDuration refreshToken;

}
