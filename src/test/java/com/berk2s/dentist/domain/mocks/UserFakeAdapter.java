package com.berk2s.dentist.domain.mocks;

import com.berk2s.dentist.infra.adapters.user.port.UserPort;

import java.util.List;

public class UserFakeAdapter implements UserPort {
    @Override
    public void checkAuthorities(String username, List<String> authorities) {
    }
}
