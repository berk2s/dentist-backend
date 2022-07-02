package com.berk2s.dentist.integration;

import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.authority.repository.AuthorityRepository;
import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.adapters.role.repository.RoleRepository;
import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.adapters.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserEntity createUser(String givenPassword) {


        var phoneNumber = RandomStringUtils.randomAlphabetic(11);
        var user = new UserEntity();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(givenPassword));
        user.addAuthority(createAuthority());
        user.addRole(createRole());
        user.setIsAccountNonExpired(true);
        user.setIsAccountNonLocked(true);
        user.setIsCredentialsNonExpired(true);
        user.setIsEnabled(true);

        userRepository.save(user);

        return user;
    }

    public RoleEntity createRole() {
        var role = new RoleEntity();
        role.setRoleName(RandomStringUtils.randomAlphabetic(5));
        role.setRoleDescription(RandomStringUtils.randomAlphabetic(5));

        roleRepository.save(role);

        return role;
    }

    public AuthorityEntity createAuthority() {
        var authority = new AuthorityEntity();
        authority.setAuthorityName(RandomStringUtils.randomAlphabetic(5));

        authorityRepository.save(authority);

        return authority;
    }

    public String convertAuthoritiesToString(List<AuthorityEntity> authorities) {
        return authorities.stream()
                .map(AuthorityEntity::getAuthorityName)
                .collect(Collectors.joining());
    }

}
