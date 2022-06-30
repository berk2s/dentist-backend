package com.berk2s.dentist.infra.adapters.login;

import com.berk2s.dentist.domain.user.port.LoginPort;
import com.berk2s.dentist.domain.user.usecase.AuthenticateUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginAdapter implements LoginPort {

    @Override
    public boolean authenticate(AuthenticateUser authenticateUser) {
        return false;
    }

}
