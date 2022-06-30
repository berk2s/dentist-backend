package com.berk2s.dentist.infra.adapters.user.port;

import java.util.List;

public interface UserPort {

    void checkAuthorities(String username, List<String> authorities);
}
