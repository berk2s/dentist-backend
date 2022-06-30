package com.berk2s.dentist.infra.adapters.login.controllers;

import com.berk2s.dentist.domain.authentication.model.Token;
import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import com.berk2s.dentist.infra.adapters.login.controllers.dtos.LoginRequest;
import com.berk2s.dentist.infra.adapters.login.controllers.dtos.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(LoginController.ENDPOINT)
@RestController
public class LoginController {

    public static final String ENDPOINT = "/login";

    private final UseCaseHandler<Token, AuthenticateUser> authenticateUserUseCaseHandler;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        var token = authenticateUserUseCaseHandler
                .handle(loginRequest.toUseCase());

        return new ResponseEntity<>(LoginResponse.fromModel(token), HttpStatus.OK);
    }

}
