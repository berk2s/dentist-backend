package com.berk2s.dentist.infra.adapters.authority.facade;

import com.berk2s.dentist.domain.error.ErrorDesc;
import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.authority.repository.AuthorityRepository;
import com.berk2s.dentist.infra.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorityFacade {

    private final AuthorityRepository authorityRepository;

    public AuthorityEntity save(AuthorityEntity authority) {
        return authorityRepository.save(authority);
    }

    public AuthorityEntity findByAuthorityName(String authorityName) {
        return authorityRepository
                .findByAuthorityName(authorityName)
                .orElseThrow(() -> {
                   log.warn("Authority by given name does not exists [authorityName: {}]", authorityName);
                   throw new EntityNotFoundException(ErrorDesc.AUTHORITY_NOT_FOUND.getDesc());
                });
    }

    public boolean existsByName(String authorityName) {
        return authorityRepository
                .existsByAuthorityName(authorityName);
    }
}
