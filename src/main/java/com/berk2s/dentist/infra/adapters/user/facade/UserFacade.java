package com.berk2s.dentist.infra.adapters.user.facade;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.adapters.user.repository.UserRepository;
import com.berk2s.dentist.infra.exceptions.EntityNotFoundException;
import com.berk2s.dentist.domain.error.ErrorDesc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserRepository userRepository;

    /**
     * Finds User by given phone number
     */
    public UserEntity findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> {
                    log.warn("User with given phone number does not exists [phoneNumber: {}]", phoneNumber);
                    throw new EntityNotFoundException(ErrorDesc.USER_NOT_FOUND.getDesc());
                });
    }

    /**
     * Finds User by given user id
     */
    public UserEntity findByUserId(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("User with given user id does not exists [userId: {}]", userId.toString());
                    throw new EntityNotFoundException(ErrorDesc.USER_NOT_FOUND.getDesc());
                });
    }
}
