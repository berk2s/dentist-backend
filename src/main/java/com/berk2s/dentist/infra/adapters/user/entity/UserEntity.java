package com.berk2s.dentist.infra.adapters.user.entity;

import com.berk2s.dentist.domain.user.model.User;
import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.entities.UUIDIdentifierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class UserEntity extends UUIDIdentifierEntity {

    @Column(unique = true)
    private String phoneNumber;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<AuthorityEntity> authorities = new ArrayList<>();

    @Column
    private Boolean isAccountNonExpired;

    @Column
    private Boolean isAccountNonLocked;

    @Column
    private Boolean isCredentialsNonExpired;

    @Column
    private Boolean isEnabled;

    public void addRole(RoleEntity role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public void addAuthority(AuthorityEntity authority) {
        if (!authorities.contains(authority)) {
            authorities.add(authority);
        }
    }

    public User toModel() {
        return User.builder()
                .userId(getId())
                .password(password)
                .phoneNumber(phoneNumber)
                .roles(roles.stream().map(RoleEntity::getRoleName).collect(Collectors.toList()))
                .build();
    }
}
