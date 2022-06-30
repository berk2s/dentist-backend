package com.berk2s.dentist.domain.user.port;

import com.berk2s.dentist.domain.user.model.User;
import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;

public interface LoginPort {

    /**
     * Checks given username and password is correct or not
     */
    User authenticate(AuthenticateUser authenticateUser);

}
