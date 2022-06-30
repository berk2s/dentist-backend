package com.berk2s.dentist.domain.mocks;

import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import com.berk2s.dentist.domain.user.model.User;
import com.berk2s.dentist.domain.user.port.LoginPort;

import java.util.List;
import java.util.UUID;

public class LoginFakeAdapter implements LoginPort {

    @Override
    public User authenticate(AuthenticateUser authenticateUser) {
        return User.builder()
                .userId(UUID.randomUUID())
                .phoneNumber(authenticateUser.getUsername())
                .password(authenticateUser.getPassword())
                .roles(List.of("ROLE_ADMIN"))
                .build();
    }
}
