package com.berk2s.dentist.infra.adapters.authority;

import com.berk2s.dentist.domain.authority.model.Authority;
import com.berk2s.dentist.domain.authority.port.AuthorityPort;
import com.berk2s.dentist.domain.authority.usecase.CreateAuthority;
import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.authority.facade.AuthorityFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorityAdapter implements AuthorityPort {

    private final AuthorityFacade authorityFacade;

    @Override
    public Authority create(CreateAuthority createAuthority) {
        var authority = new AuthorityEntity();
        authority.setAuthorityName(createAuthority.getAuthorityName());

        return authorityFacade.save(authority).toModel();
    }

    @Override
    public Authority retrieveByName(String authorityName) {
        return authorityFacade.findByAuthorityName(authorityName).toModel();
    }

    @Override
    public boolean existsByName(String authorityName) {
        return authorityFacade
                .existsByName(authorityName);
    }
}
