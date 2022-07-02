package com.berk2s.dentist.domain.mocks;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.port.RolePort;
import com.berk2s.dentist.domain.role.usecase.CreateRole;

import java.time.LocalDateTime;

public class RoleFakeAdapter implements RolePort {
    @Override
    public Role create(CreateRole createRole) {
        return Role.builder()
                .id(1L)
                .roleName(createRole.getRoleName())
                .roleDescription(createRole.getRoleDescription())
                .authorities(createRole.getAuthorities())
                .createdAt(LocalDateTime.now())
                .lastModifiedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public boolean isRoleNameTaken(String roleName) {
        if(roleName.equals("NOT_UNIQUE")) {
            return true;
        }

        return false;
    }
}
