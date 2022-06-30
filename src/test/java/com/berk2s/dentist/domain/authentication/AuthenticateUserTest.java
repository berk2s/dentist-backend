package com.berk2s.dentist.domain.authentication;

import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import com.berk2s.dentist.domain.authentication.usecase.handler.AuthenticateUserUseCaseHandler;
import com.berk2s.dentist.domain.mocks.LoginFakeAdapter;
import com.berk2s.dentist.domain.mocks.TokenFakeAdapter;
import com.berk2s.dentist.domain.mocks.UserFakeAdapter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticateUserTest {

    private AuthenticateUserUseCaseHandler authenticateUserUseCaseHandler;

    @BeforeEach
    void setUp() {
        authenticateUserUseCaseHandler = new AuthenticateUserUseCaseHandler(
                new UserFakeAdapter(),
                new LoginFakeAdapter(),
                new TokenFakeAdapter()
        );
    }

    @DisplayName("Should login successfully")
    @Test
    void shouldLoginSuccessfully() {
        // Given
        var authenticateUser = AuthenticateUser.builder()
                .username(RandomStringUtils.randomAlphabetic(48))
                .password(RandomStringUtils.randomAlphabetic(48))
                .scopes(List.of(RandomStringUtils.randomAlphabetic(48)))
                .build();

        // When
        var token = authenticateUserUseCaseHandler
                .handle(authenticateUser);

        // Then
        assertThat(token).isNotNull();
        assertThat(token.getAccessToken()).isNotNull();
        assertThat(token.getRefreshToken()).isNotNull();
        assertThat(token.getExpiresIn()).isNotNull();
    }

}
