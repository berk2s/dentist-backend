package com.berk2s.dentist.infra.adapters.role.controllers.dtos;

import com.berk2s.dentist.domain.role.model.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RoleResponse {

    private Long id;
    private String roleName;
    private String roleDescription;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public static RoleResponse fromModel(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .roleDescription(role.getRoleDescription())
                .createdAt(role.getCreatedAt())
                .lastModifiedAt(role.getLastModifiedAt())
                .build();
    }
}
