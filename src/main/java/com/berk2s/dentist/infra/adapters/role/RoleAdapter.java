package com.berk2s.dentist.infra.adapters.role;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.port.RolePort;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.authority.facade.AuthorityFacade;
import com.berk2s.dentist.infra.adapters.authority.repository.AuthorityRepository;
import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.adapters.role.facade.RoleFacade;
import com.berk2s.dentist.infra.adapters.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleAdapter implements RolePort {

    private final RoleFacade roleFacade;
    private final AuthorityFacade authorityFacade;

    @Override
    public Role create(CreateRole createRole) {
        var role = new RoleEntity();
        role.setRoleName(createRole.getRoleName());
        role.setRoleDescription(createRole.getRoleDescription());

        createRole.getAuthorities().forEach(authorityName -> {
            var authority = authorityFacade.findByAuthorityName(authorityName);
            role.addAuthority(authority);
        });

        return roleFacade.save(role).toModel();
    }

    @Override
    public boolean isRoleNameTaken(String roleName) {
        return roleFacade.existsByRoleName(roleName);
    }
}
