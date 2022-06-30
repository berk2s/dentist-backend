package com.berk2s.dentist.infra.security;

import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    private final SecurityUserDetailsService securityUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Checks user exists or not
     * If the User exists, then compares given password and correct one
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var phoneNumber = authentication.getName();
        var givenPass = authentication.getPrincipal().toString();

        var securityUser = securityUserDetailsService
                .loadUserByUsername(phoneNumber);

        if (!passwordEncoder.matches(givenPass, securityUser.getPassword())) {
            log.warn("Passwords are not matching [userId: {}]", securityUser.getId());
            throw new BadCredentialsException(ErrorDesc.INVALID_CREDENTIALS.getDesc());
        }

        return new UsernamePasswordAuthenticationToken(phoneNumber, givenPass,
                securityUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
