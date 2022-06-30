package com.berk2s.dentist.domain.user.usecase.handler;

import com.berk2s.dentist.domain.annotation.DomainService;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import com.berk2s.dentist.domain.user.model.User;
import com.berk2s.dentist.domain.user.usecase.AuthenticateUser;

@DomainService
public class AuthenticateUserUseCaseHandler implements UseCaseHandler<User, AuthenticateUser> {
    @Override
    public User handle(AuthenticateUser authenticateUser) {
        return null;
    }
}
