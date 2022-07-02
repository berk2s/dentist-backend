package com.berk2s.dentist.infra.adapters.authority.repository;

import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

    /**
     * Finds authority by authority name
     */
    Optional<AuthorityEntity> findByAuthorityName(String authorityName);

    boolean existsByAuthorityName(String authorityName);

}
