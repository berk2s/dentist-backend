package com.berk2s.dentist.infra.adapters.user;

import com.berk2s.dentist.infra.adapters.user.exceptions.InsufficientAuthorityException;
import com.berk2s.dentist.infra.adapters.user.facade.UserFacade;
import com.berk2s.dentist.infra.adapters.user.port.UserPort;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAdapter implements UserPort {

    private final UserFacade userFacade;

    /**
     * Checks requested authorities are belongs to user or not
     */
    @Override
    public void checkAuthorities(String username,
                                 List<String> authorities) {
        authorities
                .stream()
                .map(i -> i.toUpperCase(Locale.ROOT))
                .forEach(checkAuthority(username));
    }

    private Consumer<String> checkAuthority(String username) {
        return authority -> {
            var user = userFacade.findByPhoneNumber(username);

            var anyMatch = user.getAuthorities().stream()
                    .map(i -> i.getAuthorityName().toUpperCase(Locale.ROOT))
                    .anyMatch(i -> i.equals(authority));

            if (!anyMatch) {
                log.warn("User has not permission for some requested authorities");
                throw new InsufficientAuthorityException(ErrorDesc.INSUFFICIENT_AUTHORITY.getDesc());
            }
        };
    }
}
