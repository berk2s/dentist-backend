package com.berk2s.dentist.infra.adapters.role.facade;

import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import com.berk2s.dentist.infra.adapters.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleFacade {

    private final RoleRepository roleRepository;

    public RoleEntity save(RoleEntity role) {
        return roleRepository.save(role);
    }

    public boolean existsByRoleName(String roleName) {
        return roleRepository.existsByRoleName(roleName);
    }
}
