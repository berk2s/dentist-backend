package com.berk2s.dentist.infra.adapters.role.entity;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.entities.LongIdentifierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class RoleEntity extends LongIdentifierEntity {

    @Column
    private String roleName;

    @Column
    private String roleDescription;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<AuthorityEntity> authorities = new ArrayList<>();

    public void addAuthority(AuthorityEntity authority) {
        if (!authorities.contains(authority)) {
            authorities.add(authority);
        }
    }

    public void removeAuthority(AuthorityEntity authority) {
        authorities.remove(authority);
    }

    public Role toModel() {
        return Role.builder()
                .id(getId())
                .roleName(roleName)
                .roleDescription(roleDescription)
                .authorities(toAuthorityNames())
                .createdAt(getCreatedAt())
                .lastModifiedAt(getLastModifiedAt())
                .build();
    }

    private List<String> toAuthorityNames() {
        return authorities.stream()
                .map(AuthorityEntity::getAuthorityName)
                .collect(Collectors.toList());
    }
}
