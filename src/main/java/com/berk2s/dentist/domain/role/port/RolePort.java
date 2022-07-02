package com.berk2s.dentist.domain.role.port;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.usecase.CreateRole;

public interface RolePort {

    /**
     * Creates role
     */
    Role create(CreateRole createRole);

    /**
     * Checks whether given role name unique or not
     */
    boolean isRoleNameTaken(String roleName);
}
