package com.berk2s.dentist.infra.adapters.helpers;

import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public final class MockCreator {

    public static UserEntity mockUser() {
        var userEntity = new UserEntity();
        userEntity.setPhoneNumber(RandomStringUtils.randomAlphabetic(10));
        userEntity.setPassword(RandomStringUtils.randomAlphabetic(10));
        userEntity.setRoles(List.of(mockRole(), mockRole()));
        userEntity.setAuthorities(List.of(mockAuthority(), mockAuthority()));
        userEntity.setIsAccountNonLocked(true);
        userEntity.setIsAccountNonExpired(true);
        userEntity.setIsEnabled(true);
        userEntity.setIsCredentialsNonExpired(true);

        return userEntity;
    }

    public static AuthorityEntity mockAuthority() {
        var authorityEntity = new AuthorityEntity();
        authorityEntity.setId(1L);
        authorityEntity.setAuthorityName(RandomStringUtils.randomAlphabetic(6));

        return authorityEntity;
    }

    public static RoleEntity mockRole() {
        var roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRoleName(RandomStringUtils.randomAlphabetic(6));

        return roleEntity;
    }

    public static Authentication mockAuthentication() {
        return new UsernamePasswordAuthenticationToken(
                RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6),
                List.of(new SimpleGrantedAuthority(RandomStringUtils.randomAlphabetic(6))));
    }

}
