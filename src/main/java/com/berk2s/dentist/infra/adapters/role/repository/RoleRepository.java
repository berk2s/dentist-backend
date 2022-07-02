package com.berk2s.dentist.infra.adapters.role.repository;

import com.berk2s.dentist.infra.adapters.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Checks if Role exists by given role name
     */
    boolean existsByRoleName(String roleName);


}
