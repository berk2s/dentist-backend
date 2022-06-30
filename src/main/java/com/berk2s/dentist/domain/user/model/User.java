package com.berk2s.dentist.domain.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String phoneNumber;
    private String password;
}
