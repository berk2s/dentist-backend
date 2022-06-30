package com.berk2s.dentist.infra.security;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.berk2s.dentist.infra.adapters.helpers.MockCreator.mockUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityAuthenticationProviderTest {

    @Mock
    SecurityUserDetailsService securityUserDetailsService;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    SecurityAuthenticationProvider securityAuthenticationProvider;

    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = mockUser();
    }

    @DisplayName("Should authenticate successfully")
    @Test
    void shouldAuthenticateSuccessfully() {
        // Given
        when(securityUserDetailsService.loadUserByUsername(any())).thenReturn(new SecurityUserImpl(userEntity));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        var authToken = new UsernamePasswordAuthenticationToken(userEntity.getPhoneNumber(),
                userEntity.getPassword());

        // When
        var returnedToken = securityAuthenticationProvider.authenticate(authToken);

        // Then
        assertThat(returnedToken).isNotNull()
                        .returns(userEntity.getPhoneNumber(), Authentication::getName)
                        .returns(userEntity.getPassword(), Authentication::getCredentials);

        assertThat(returnedToken.getAuthorities().size())
                .isEqualTo(userEntity.getRoles().size() + userEntity.getAuthorities().size());

        verify(securityUserDetailsService, times((1))).loadUserByUsername(any());
        verify(passwordEncoder, times((1))).matches(any(), any());
    }
}