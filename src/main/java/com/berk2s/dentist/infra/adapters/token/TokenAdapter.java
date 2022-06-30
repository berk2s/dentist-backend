package com.berk2s.dentist.infra.adapters.token;

import com.berk2s.dentist.domain.token.model.Token;
import com.berk2s.dentist.domain.token.port.TokenPort;
import com.berk2s.dentist.domain.token.usecase.GenerateToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAdapter implements TokenPort {
    @Override
    public Token generateToken(GenerateToken generateToken) {
        return null;
    }
}
