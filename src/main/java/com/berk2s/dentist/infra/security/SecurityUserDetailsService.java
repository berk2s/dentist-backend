package com.berk2s.dentist.infra.security;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.adapters.user.facade.UserFacade;
import com.berk2s.dentist.infra.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserFacade userFacade;

    /**
     * Looks user exists or not by given unique indicator
     */
    @Override
    public SecurityUser loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        try {
            UserEntity user = userFacade.findByPhoneNumber(phoneNumber);
            return new SecurityUserImpl(user);
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
