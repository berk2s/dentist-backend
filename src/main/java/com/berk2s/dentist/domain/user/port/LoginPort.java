package com.berk2s.dentist.domain.user.port;

import com.berk2s.dentist.domain.user.usecase.AuthenticateUser;

public interface LoginPort {

    /**
     * Checks given username and password is correct or not
     */
    boolean authenticate(AuthenticateUser authenticateUser);

}
