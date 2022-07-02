package com.berk2s.dentist.domain.role.usecase.handler;

import com.berk2s.dentist.domain.annotation.DomainService;
import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.port.RolePort;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@DomainService
public class CreateRoleUseCaseHandler implements UseCaseHandler<Role, CreateRole> {

    private final RolePort rolePort;

    @Override
    public Role handle(CreateRole createRole) {
        return rolePort.create(createRole);
    }
}
