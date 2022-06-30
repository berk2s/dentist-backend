package com.berk2s.dentist.infra.adapters.user.repository;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, UUID> {

    /**
     * Finds user by given phone number
     */
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

}
