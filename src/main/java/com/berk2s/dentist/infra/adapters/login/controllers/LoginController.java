package com.berk2s.dentist.infra.adapters.login.controllers;

import com.berk2s.dentist.infra.adapters.login.controllers.dtos.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login() {
        return new ResponseEntity<>(LoginResponse.builder()
                .accessToken("access token")
                .refreshToken("refresh token")
                .expiresIn(1L)
                .build(), HttpStatus.OK);
    }

}
