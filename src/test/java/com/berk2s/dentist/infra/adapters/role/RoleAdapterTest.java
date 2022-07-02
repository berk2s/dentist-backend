package com.berk2s.dentist.infra.adapters.role;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.authority.facade.AuthorityFacade;
import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.adapters.role.facade.RoleFacade;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleAdapterTest {

    @Mock
    RoleFacade roleFacade;

    @Mock
    AuthorityFacade authorityFacade;

    @InjectMocks
    RoleAdapter roleAdapter;

    CreateRole createRole;

    @BeforeEach
    void setUp() {
        createRole = CreateRole.builder()
                .roleName(RandomStringUtils.randomAlphabetic(8))
                .roleDescription(RandomStringUtils.randomAlphabetic(20))
                .authorities(List.of(RandomStringUtils.randomAlphabetic(10)))
                .build();
    }

    @DisplayName("Should create role successfully")
    @Test
    void shouldCreateRoleSuccessfully() {
        // Given
        when(roleFacade.save(any())).thenAnswer(createRoleEntity());
        when(authorityFacade.findByAuthorityName(any())).thenAnswer(createAuthorityEntity());

        // When
        var role = roleAdapter.create(createRole);

        // Then
        assertThat(role).isNotNull()
                .returns(createRole.getRoleName(), Role::getRoleName)
                .returns(createRole.getRoleDescription(), Role::getRoleDescription);

        assertThat(role.getAuthorities())
                .contains(createRole.getAuthorities().get(0));

        assertThat(role.getId()).isNotNull();
        assertThat(role.getCreatedAt()).isNotNull();
        assertThat(role.getLastModifiedAt()).isNotNull();

        verify(roleFacade, times(1)).save(any());
        verify(authorityFacade, times(1)).findByAuthorityName(any());
    }

    private Answer<Object> createRoleEntity() {
        return i -> {
            var role = (RoleEntity) i.getArguments()[0];
            role.setId(1L);
            role.setCreatedAt(LocalDateTime.now());
            role.setLastModifiedAt(LocalDateTime.now());
            return role;
        };
    }

    private Answer<Object> createAuthorityEntity() {
        return i -> {
            var authorityName = (String) i.getArguments()[0];

            return createAuthority(authorityName);
        };
    }

    private AuthorityEntity createAuthority(String authorityName) {
        var authority = new AuthorityEntity();
        authority.setAuthorityName(authorityName);

        return authority;
    }

}