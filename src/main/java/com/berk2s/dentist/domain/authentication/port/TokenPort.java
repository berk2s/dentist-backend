package com.berk2s.dentist.domain.authentication.port;

import com.berk2s.dentist.domain.authentication.event.GenerateToken;
import com.berk2s.dentist.domain.authentication.model.Token;

public interface TokenPort {
    /**
     * Generates token by given user id and scopes
     */
    Token generateTokens(GenerateToken generateToken);
}
