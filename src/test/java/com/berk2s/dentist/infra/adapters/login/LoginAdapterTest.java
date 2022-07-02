package com.berk2s.dentist.infra.adapters.login;

import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.adapters.user.facade.UserFacade;
import com.berk2s.dentist.domain.error.ErrorDesc;
import com.berk2s.dentist.infra.security.SecurityAuthenticationProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.List;

import static com.berk2s.dentist.infra.adapters.helpers.MockCreator.mockAuthentication;
import static com.berk2s.dentist.infra.adapters.helpers.MockCreator.mockUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginAdapterTest {

    @Mock
    UserFacade userFacade;

    @Mock
    SecurityAuthenticationProvider securityAuthenticationProvider;

    @InjectMocks
    LoginAdapter loginAdapter;

    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = mockUser();
    }

    @DisplayName("Should login successfully")
    @Test
    void shouldLoginSuccessfully() {
        // Given
        when(securityAuthenticationProvider.authenticate(any())).thenReturn(mockAuthentication());
        when(userFacade.findByPhoneNumber(any())).thenReturn(userEntity);

        var authenticateUser = AuthenticateUser.builder()
                .username(RandomStringUtils.randomAlphabetic(40))
                .password(RandomStringUtils.randomAlphabetic(40))
                .scopes(List.of(RandomStringUtils.randomAlphabetic(40)))
                .build();
        // When
        var user = loginAdapter.authenticate(authenticateUser);

        // Then
        verify(userFacade, times(1)).findByPhoneNumber(any());
        verify(securityAuthenticationProvider, times(1)).authenticate(any());
    }

    @DisplayName("Should invalid credentials return error")
    @Test
    void shouldInvalidCredentialReturnError() {
        // Given
        when(securityAuthenticationProvider.authenticate(any()))
                .thenThrow(new BadCredentialsException(ErrorDesc.INVALID_CREDENTIALS.getDesc()));

        var authenticateUser = AuthenticateUser.builder()
                .username(RandomStringUtils.randomAlphabetic(40))
                .password("Invalid password")
                .scopes(List.of(RandomStringUtils.randomAlphabetic(40)))
                .build();

        // When
        BadCredentialsException exception = assertThrows(BadCredentialsException.class,
                () -> loginAdapter.authenticate(authenticateUser));

        // Then
        assertThat(exception.getMessage())
                .isEqualTo(ErrorDesc.INVALID_CREDENTIALS.getDesc());

        verify(securityAuthenticationProvider, times(1)).authenticate(any());
    }
}