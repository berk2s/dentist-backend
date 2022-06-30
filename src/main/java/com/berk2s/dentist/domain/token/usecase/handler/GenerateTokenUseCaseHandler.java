package com.berk2s.dentist.domain.token.usecase.handler;

import com.berk2s.dentist.domain.token.model.Token;
import com.berk2s.dentist.domain.token.usecase.GenerateToken;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;

public class GenerateTokenUseCaseHandler implements UseCaseHandler<Token, GenerateToken> {
    @Override
    public Token handle(GenerateToken generateToken) {
        return null;
    }
}
