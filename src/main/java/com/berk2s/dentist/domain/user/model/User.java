package com.berk2s.dentist.domain.user.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class User {

    private UUID userId;
    private String phoneNumber;
    private String password;
    private List<String> roles;
}
