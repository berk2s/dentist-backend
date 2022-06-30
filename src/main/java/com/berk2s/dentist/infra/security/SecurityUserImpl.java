package com.berk2s.dentist.infra.security;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class SecurityUserImpl implements SecurityUser {

    private final UserEntity user;

    public SecurityUserImpl(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();

        user.getAuthorities()
                .forEach(authority -> grantedAuthorities.add(createAuthority(authority.getAuthorityName())));

        user.getRoles()
                .forEach(role -> grantedAuthorities.add(createAuthority("ROLE_" + role.getRoleName())));

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }

    private SimpleGrantedAuthority createAuthority(String authority) {
        return new SimpleGrantedAuthority(authority.toUpperCase());
    }

    @Override
    public UUID getId() {
        return user.getId();
    }
}
