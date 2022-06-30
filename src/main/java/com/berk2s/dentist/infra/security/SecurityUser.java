package com.berk2s.dentist.infra.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface SecurityUser extends UserDetails {

    UUID getId();

}
