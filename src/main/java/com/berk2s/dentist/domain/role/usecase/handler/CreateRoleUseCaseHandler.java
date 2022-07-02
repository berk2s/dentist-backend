package com.berk2s.dentist.domain.role.usecase.handler;

import com.berk2s.dentist.domain.annotation.DomainService;
import com.berk2s.dentist.domain.authority.port.AuthorityPort;
import com.berk2s.dentist.domain.authority.usecase.CreateAuthority;
import com.berk2s.dentist.domain.error.ErrorDesc;
import com.berk2s.dentist.domain.role.exception.RoleNameTakenException;
import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.port.RolePort;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@DomainService
public class CreateRoleUseCaseHandler implements UseCaseHandler<Role, CreateRole> {

    private final RolePort rolePort;
    private final AuthorityPort authorityPort;

    @Override
    public Role handle(CreateRole createRole) {
        if (rolePort.isRoleNameTaken(createRole.getRoleName())) {
            log.warn("Given role name already exists. [roleName: {}]", createRole.getRoleName());
            throw new RoleNameTakenException(ErrorDesc.ROLE_NAME_ALREADY_TAKEN.getDesc());
        }

        var authorities = createRole.getAuthorities()
                .stream()
                .map(String::toUpperCase)
                .map(toAuthorityIDs())
                .collect(Collectors.toList());

        createRole.setAuthorities(authorities);

        return rolePort.create(createRole);
    }

    private Function<String, String> toAuthorityIDs() {
        return authorityName -> {
            if (!authorityPort.existsByName(authorityName)) {
                return authorityPort.create(createAuthority(authorityName)).getAuthorityName();
            }

            return authorityPort.retrieveByName(authorityName).getAuthorityName();
        };
    }

    private CreateAuthority createAuthority(String authorityName) {
        return CreateAuthority.builder()
                .authorityName(authorityName)
                .build();
    }
}
