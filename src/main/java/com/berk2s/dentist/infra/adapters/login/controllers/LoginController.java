package com.berk2s.dentist.infra.adapters.login.controllers;

import com.berk2s.dentist.domain.authentication.model.Token;
import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import com.berk2s.dentist.infra.adapters.login.controllers.dtos.LoginRequest;
import com.berk2s.dentist.infra.adapters.login.controllers.dtos.LoginResponse;
import com.berk2s.dentist.infra.config.swagger.SwaggerExample;
import com.berk2s.dentist.infra.exceptions.handler.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Login", description = "Contains an endpoint that users log in")
@RequiredArgsConstructor
@RequestMapping(LoginController.ENDPOINT)
@RestController
public class LoginController {

    public static final String ENDPOINT = "/login";

    private final UseCaseHandler<Token, AuthenticateUser> authenticateUserUseCaseHandler;

    @Operation(summary = "Login operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class),
                            examples = {
                                    @ExampleObject(name = "Successfully user logged", value = SwaggerExample.LOGIN_SUCCESSFUL)
                            })
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "Bad request", value = SwaggerExample.INVALID_LOGIN_REQUEST_BODY)
                            })
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "Bad credentials", value = SwaggerExample.INVALID_CREDENTIALS),
                                    @ExampleObject(name = "Insufficient authority",
                                            description = "Returns when given scopes in request body are not belongs to User",
                                            value = SwaggerExample.INSUFFICIENT_AUTHORITY_ERROR),
                            })
            }),
            @ApiResponse(responseCode = "404", description = "Something Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "Username not found",
                                            description = "Altough username does not exists, returns bad credentials error",
                                            value = SwaggerExample.INVALID_USERNAME_ERROR),
                            })
            }),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        var token = authenticateUserUseCaseHandler
                .handle(loginRequest.toUseCase());

        return new ResponseEntity<>(LoginResponse.fromModel(token), HttpStatus.OK);
    }

}
