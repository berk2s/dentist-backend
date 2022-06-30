package com.berk2s.dentist.domain.authentication.usecase.handler;

import com.berk2s.dentist.domain.annotation.DomainService;
import com.berk2s.dentist.domain.authentication.event.GenerateToken;
import com.berk2s.dentist.domain.authentication.model.Token;
import com.berk2s.dentist.domain.authentication.port.TokenPort;
import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import com.berk2s.dentist.domain.user.port.LoginPort;
import com.berk2s.dentist.infra.adapters.user.exceptions.InsufficientAuthorityException;
import com.berk2s.dentist.infra.adapters.user.port.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@DomainService
public class AuthenticateUserUseCaseHandler implements UseCaseHandler<Token, AuthenticateUser> {

    private final UserPort userPort;
    private final LoginPort loginPort;
    private final TokenPort tokenPort;

    /**
     * Generates tokens by given user id
     */
    @Override
    public Token handle(AuthenticateUser authenticateUser) {
        userPort.checkAuthorities(authenticateUser.getUsername(),
                authenticateUser.getScopes());

        var user = loginPort.authenticate(authenticateUser);

        var claims = new HashMap<String, Object>();
        claims.put("roles", user.getRoles());

        return tokenPort.generateTokens(generateToken(user.getUserId(),
                authenticateUser.getScopes(),
                claims));
    }

    private GenerateToken generateToken(UUID userid,
                                        List<String> scopes,
                                        HashMap<String, Object> claims) {
        return GenerateToken.builder()
                .userId(userid)
                .scopes(scopes)
                .claims(claims)
                .build();
    }
}
