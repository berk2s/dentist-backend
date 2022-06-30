package com.berk2s.dentist.infra.adapters.token;

import com.berk2s.dentist.domain.authentication.event.GenerateToken;
import com.berk2s.dentist.domain.authentication.model.Token;
import com.berk2s.dentist.domain.authentication.port.TokenPort;
import com.berk2s.dentist.infra.adapters.token.facade.AccessTokenFacade;
import com.berk2s.dentist.infra.adapters.token.facade.RefreshTokenFacade;
import com.berk2s.dentist.infra.config.TokenConfiguration;
import com.berk2s.dentist.infra.time.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAdapter implements TokenPort {

    private final AccessTokenFacade accessTokenFacade;
    private final RefreshTokenFacade refreshTokenFacade;
    private final TokenConfiguration tokenConfiguration;

    /**
     * Generates token for the given User
     */
    @Override
    public Token generateTokens(GenerateToken generateToken) {
        var accessToken = accessTokenFacade.createJwt(generateTokenForAccessToken(generateToken));
        var refreshToken = refreshTokenFacade.createRefreshToken(generateTokenForRefreshToken(generateToken));

        return Token.builder()
                .accessToken(accessToken.serialize())
                .refreshToken(refreshToken.getToken())
                .expiresIn(TimeUtils.toLocalDateTime(Optional.ofNullable(generateToken.getExpiryDateTime())))
                .build();
    }

    private GenerateToken generateTokenForAccessToken(GenerateToken generateToken) {
        return GenerateToken.builder()
                .userId(generateToken.getUserId())
                .scopes(generateToken.getScopes())
                .claims(generateToken.getClaims())
                .notBeforeTime(Duration.ZERO)
                .expiryDateTime(tokenConfiguration.getAccessToken().getLifetime())
                .build();
    }

    private GenerateToken generateTokenForRefreshToken(GenerateToken generateToken) {
        return GenerateToken.builder()
                .userId(generateToken.getUserId())
                .scopes(generateToken.getScopes())
                .claims(generateToken.getClaims())
                .notBeforeTime(Duration.ZERO)
                .expiryDateTime(tokenConfiguration.getRefreshToken().getLifetime())
                .build();
    }
}
