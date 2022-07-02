package com.berk2s.dentist.infra.adapters.role.entity;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.infra.entities.LongIdentifierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class RoleEntity extends LongIdentifierEntity {

    @Column
    private String roleName;

    @Column
    private String roleDescription;

    public Role toModel() {
        return Role.builder()
                .id(getId())
                .roleName(roleName)
                .roleDescription(roleDescription)
                .createdAt(getCreatedAt())
                .lastModifiedAt(getLastModifiedAt())
                .build();
    }
}
