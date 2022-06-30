package com.berk2s.dentist.infra.adapters.token.repository;

import com.berk2s.dentist.infra.adapters.token.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
