package com.berk2s.dentist.domain.mocks;

import com.berk2s.dentist.domain.authority.model.Authority;
import com.berk2s.dentist.domain.authority.port.AuthorityPort;
import com.berk2s.dentist.domain.authority.usecase.CreateAuthority;

public class AuthorityFakeAdapter implements AuthorityPort {
    @Override
    public Authority create(CreateAuthority createAuthority) {
        return Authority.builder()
                .authorityName(createAuthority.getAuthorityName())
                .build();
    }

    @Override
    public Authority retrieveByName(String authorityName) {
        return Authority.builder()
                .authorityName(authorityName)
                .build();
    }

    @Override
    public boolean existsByName(String authorityName) {
        return true;
    }
}
