package com.berk2s.dentist.domain.token.port;

import com.berk2s.dentist.domain.token.model.Token;
import com.berk2s.dentist.domain.token.usecase.GenerateToken;

public interface TokenPort {
    /**
     * Generates token by given user id and scopes
     */
    Token generateToken(GenerateToken generateToken);
}
