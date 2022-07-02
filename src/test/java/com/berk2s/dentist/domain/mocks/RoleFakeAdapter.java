package com.berk2s.dentist.domain.mocks;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.port.RolePort;
import com.berk2s.dentist.domain.role.usecase.CreateRole;

import java.time.LocalDateTime;

public class RoleFakeAdapter implements RolePort {
    @Override
    public Role create(CreateRole createRole) {
        if(createRole.getRoleName().equals("NOT_UNIQUE")) {
            throw new RuntimeException("");
        }

        return Role.builder()
                .id(1L)
                .roleName(createRole.getRoleName())
                .roleDescription(createRole.getRoleDescription())
                .createdAt(LocalDateTime.now())
                .lastModifiedAt(LocalDateTime.now())
                .build();
    }
}
