package com.berk2s.dentist.domain.role.port;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.usecase.CreateRole;

public interface RolePort {

    /**
     * Creates role
     */
    Role create(CreateRole createRole);
}
