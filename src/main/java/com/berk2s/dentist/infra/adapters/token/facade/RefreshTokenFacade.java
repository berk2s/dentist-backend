package com.berk2s.dentist.infra.adapters.token.facade;

import com.berk2s.dentist.domain.authentication.event.GenerateToken;
import com.berk2s.dentist.infra.adapters.token.entity.RefreshToken;
import com.berk2s.dentist.infra.adapters.token.repository.RefreshTokenRepository;
import com.berk2s.dentist.infra.adapters.user.facade.UserFacade;
import com.berk2s.dentist.infra.time.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenFacade {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserFacade userFacade;

    /**
     * Creates refresh token
     */
    public RefreshToken createRefreshToken(GenerateToken generateToken) {
        var user = userFacade.findByUserId(generateToken.getUserId());

        var refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(generateRandomTokenRaw());
        refreshToken.setIssueTime(LocalDateTime.now());
        refreshToken.setExpiryDateTime(TimeUtils.toLocalDateTime(Optional.ofNullable(generateToken.getExpiryDateTime())));
        refreshToken.setNotBefore(TimeUtils.toLocalDateTime(Optional.ofNullable(generateToken.getNotBeforeTime())));

        refreshTokenRepository.save(refreshToken);

        log.info("Refresh token created for the User [userId: {}, refreshTokenId: {}]",
                generateToken.getUserId(), refreshToken.getId());

        return refreshToken;
    }

    /**
     * Generates random refresh token
     */
    private String generateRandomTokenRaw() {
        return RandomStringUtils.randomAlphabetic(48);
    }

}
