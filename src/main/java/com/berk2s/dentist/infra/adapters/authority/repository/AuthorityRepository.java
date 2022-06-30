package com.berk2s.dentist.infra.adapters.authority.repository;

import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
