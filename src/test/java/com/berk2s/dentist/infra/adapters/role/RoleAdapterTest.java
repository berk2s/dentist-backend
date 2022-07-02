package com.berk2s.dentist.infra.adapters.role;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.adapters.role.repository.RoleRepository;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import com.berk2s.dentist.infra.exceptions.UniquenessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleAdapterTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleAdapter roleAdapter;

    CreateRole createRole;

    @BeforeEach
    void setUp() {
        createRole = CreateRole.builder()
                .roleName(RandomStringUtils.randomAlphabetic(8))
                .roleDescription(RandomStringUtils.randomAlphabetic(20))
                .build();
    }

    @DisplayName("Should create role successfully")
    @Test
    void shouldCreateRoleSuccessfully() {
        // Given
        when(roleRepository.save(any())).thenAnswer(createRoleEntity());

        // When
        var role = roleAdapter.create(createRole);

        // Then
        assertThat(role).isNotNull()
                .returns(createRole.getRoleName(), Role::getRoleName)
                .returns(createRole.getRoleDescription(), Role::getRoleDescription);

        assertThat(role.getId()).isNotNull();
        assertThat(role.getCreatedAt()).isNotNull();
        assertThat(role.getLastModifiedAt()).isNotNull();

        verify(roleRepository, times(1)).save(any());
    }

    @DisplayName("When create role name is not unique returns exception")
    @Test
    void whenCreateRoleNameIsNotUniqueReturnsException() {
        // Given
        when(roleRepository.existsByRoleName(any())).thenReturn(true);

        // When
        UniquenessException exception = assertThrows(UniquenessException.class,
                () -> roleAdapter.create(createRole));

        // Then
        assertThat(exception.getMessage())
                .isEqualTo(ErrorDesc.ROLE_NAME_ALREADY_TAKEN.getDesc());

        verify(roleRepository, times(1)).existsByRoleName(any());
        verify(roleRepository, times(0)).save(any());
    }


    private Answer<Object> createRoleEntity() {
        return i -> {
            var role = (RoleEntity) i.getArguments()[0];

            var returnedRole = new RoleEntity();
            returnedRole.setId(1L);
            returnedRole.setRoleName(role.getRoleName());
            returnedRole.setRoleDescription(role.getRoleDescription());
            returnedRole.setCreatedAt(LocalDateTime.now());
            returnedRole.setLastModifiedAt(LocalDateTime.now());

            return returnedRole;
        };
    }
}