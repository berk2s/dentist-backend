package com.berk2s.dentist.infra.adapters.role.controllers.dtos;

import com.berk2s.dentist.domain.role.usecase.CreateRole;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class CreateRoleRequest {

    @NotEmpty(message = "createRole.roleName.empty")
    @Size(min = 3, max = 100, message = "createRole.roleName.notInRange")
    private String roleName;

    @NotEmpty(message = "createRole.roleDescription.empty")
    @Size(min = 3, max = 300, message = "createRole.roleDescription.notInRange")
    private String roleDescription;

    @NotNull(message = "createRole.roleAuthorities.empty")
    @Size(min = 1, max = 1000, message = "createRole.roleAuthorities.notInRange")
    private List<String> authorities;

    public CreateRole toUseCase() {
        return CreateRole.builder()
                .roleName(roleName)
                .roleDescription(roleDescription)
                .authorities(authorities)
                .build();
    }
}
