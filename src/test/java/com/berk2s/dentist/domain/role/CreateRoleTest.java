package com.berk2s.dentist.domain.role;

import com.berk2s.dentist.domain.mocks.RoleFakeAdapter;
import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.domain.role.usecase.handler.CreateRoleUseCaseHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CreateRoleTest {

    private CreateRoleUseCaseHandler createRoleUseCaseHandler;

    @BeforeEach
    void setUp() {
        createRoleUseCaseHandler = new CreateRoleUseCaseHandler(new RoleFakeAdapter());
    }

    @DisplayName("Should create Role successfully")
    @Test
    void shouldCreateRoleSuccessfully() {
        // Given
        var createRole = CreateRole.builder()
                .roleName(RandomStringUtils.randomAlphabetic(8))
                .roleDescription(RandomStringUtils.randomAlphabetic(8))
                .build();
        // When
        var role = createRoleUseCaseHandler.handle(createRole);

        // Then
        assertThat(role).isNotNull()
                .returns(createRole.getRoleName(), Role::getRoleName)
                .returns(createRole.getRoleDescription(), Role::getRoleDescription);

        assertThat(role.getId()).isNotNull();
        assertThat(role.getCreatedAt()).isNotNull();
        assertThat(role.getLastModifiedAt()).isNotNull();
    }

    @DisplayName("When role name taken then return exception")
    @Test
    void whenRoleNameTakenThenReturnException() {
        // Given
        var createRole = CreateRole.builder()
                .roleName("NOT_UNIQUE")
                .roleDescription(RandomStringUtils.randomAlphabetic(8))
                .build();

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> createRoleUseCaseHandler.handle(createRole));
    }
}
