package com.berk2s.dentist.domain.mocks;

import com.berk2s.dentist.domain.authentication.event.GenerateToken;
import com.berk2s.dentist.domain.authentication.model.Token;
import com.berk2s.dentist.domain.authentication.port.TokenPort;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

public class TokenFakeAdapter implements TokenPort {
    @Override
    public Token generateTokens(GenerateToken generateToken) {
        return Token.builder()
                .accessToken(RandomStringUtils.randomAlphabetic(100))
                .refreshToken(RandomStringUtils.randomAlphabetic(48))
                .expiresIn(LocalDateTime.now())
                .build();
    }
}
