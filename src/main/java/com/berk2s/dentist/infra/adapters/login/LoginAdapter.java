package com.berk2s.dentist.infra.adapters.login;

import com.berk2s.dentist.domain.user.model.User;
import com.berk2s.dentist.domain.user.port.LoginPort;
import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import com.berk2s.dentist.infra.adapters.user.facade.UserFacade;
import com.berk2s.dentist.infra.security.SecurityAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginAdapter implements LoginPort {

    private final UserFacade userFacade;
    private final SecurityAuthenticationProvider securityAuthenticationProvider;

    /**
     * Authenticates user by given credentials
     */
    @Override
    public User authenticate(AuthenticateUser authenticateUser) {
        securityAuthenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authenticateUser.getUsername(),
                        authenticateUser.getPassword()
                ));

        return userFacade
                .findByPhoneNumber(authenticateUser.getUsername())
                .toModel();
    }


}
