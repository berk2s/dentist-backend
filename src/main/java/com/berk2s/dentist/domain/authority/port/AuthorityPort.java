package com.berk2s.dentist.domain.authority.port;

import com.berk2s.dentist.domain.authority.model.Authority;
import com.berk2s.dentist.domain.authority.usecase.CreateAuthority;

public interface AuthorityPort {

    /**
     * Creates authority
     */
    Authority create(CreateAuthority createAuthority);

    /**
     * Retrieves Authority by authority name
     */
    Authority retrieveByName(String authorityName);

    /**
     * Checks whether Authority exists or not
     */
    boolean existsByName(String authorityName);
}
