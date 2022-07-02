package com.berk2s.dentist.infra.adapters.role;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.port.RolePort;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.adapters.role.repository.RoleRepository;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import com.berk2s.dentist.infra.exceptions.UniquenessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleAdapter implements RolePort {

    private final RoleRepository roleRepository;

    @Override
    public Role create(CreateRole createRole) {
        if (roleRepository.existsByRoleName(createRole.getRoleName())) {
            log.warn("Given role name already exists. [roleName: {}]", createRole.getRoleName());
            throw new UniquenessException(ErrorDesc.ROLE_NAME_ALREADY_TAKEN.getDesc());
        }

        var role = new RoleEntity();
        role.setRoleName(createRole.getRoleName());
        role.setRoleDescription(createRole.getRoleDescription());

        return roleRepository.save(role).toModel();
    }
}
