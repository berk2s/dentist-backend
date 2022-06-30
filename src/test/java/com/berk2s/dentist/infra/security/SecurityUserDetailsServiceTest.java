package com.berk2s.dentist.infra.security;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.adapters.user.facade.UserFacade;
import com.berk2s.dentist.infra.exceptions.EntityNotFoundException;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.berk2s.dentist.infra.adapters.helpers.MockCreator.mockUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityUserDetailsServiceTest {

    @Mock
    UserFacade userFacade;

    @InjectMocks
    SecurityUserDetailsService securityUserDetailsService;

    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = mockUser();
    }

    @DisplayName("Should load user by phone number successfully")
    @Test
    void shouldLoadUserByPhoneNumberSuccessfully() {
        // Given
        when(userFacade.findByPhoneNumber(any())).thenReturn(userEntity);

        // When
        var securityUser = securityUserDetailsService
                .loadUserByUsername(userEntity.getPhoneNumber());

        // Then
        assertThat(securityUser).isNotNull()
                .returns(userEntity.getId(), SecurityUser::getId)
                .returns(userEntity.getPhoneNumber(), SecurityUser::getUsername)
                .returns(userEntity.getPassword(), SecurityUser::getPassword)
                .returns(userEntity.getIsAccountNonExpired(), SecurityUser::isAccountNonExpired)
                .returns(userEntity.getIsAccountNonLocked(), SecurityUser::isAccountNonLocked)
                .returns(userEntity.getIsCredentialsNonExpired(), SecurityUser::isCredentialsNonExpired)
                .returns(userEntity.getIsEnabled(), SecurityUser::isEnabled);

        verify(userFacade, times(1)).findByPhoneNumber(any());
    }

    @DisplayName("Should return user not found error")
    @Test
    void shouldReturnUserNotFoundError() {
        // Given
        when(userFacade.findByPhoneNumber(any()))
                .thenThrow(new EntityNotFoundException(ErrorDesc.USER_NOT_FOUND.getDesc()));

        // When
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                () -> securityUserDetailsService
                        .loadUserByUsername(userEntity.getPhoneNumber()));

        // Then
        assertThat(exception.getMessage())
                .isEqualTo(ErrorDesc.USER_NOT_FOUND.getDesc());

        verify(userFacade, times(1)).findByPhoneNumber(any());
    }

}