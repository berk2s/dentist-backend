package com.berk2s.dentist.infra.security;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.adapters.user.repository.UserRepository;
import com.berk2s.dentist.infra.exceptions.EntityNotFoundException;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Looks user exists or not by given unique indicator
     */
    @Override
    public SecurityUser loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> {
                    log.warn("User with given phone number does not exists [phoneNumber: {}]", phoneNumber);
                    throw new EntityNotFoundException(ErrorDesc.USER_NOT_FOUND.getDesc());
                });

        return new SecurityUserImpl(user);
    }
}
