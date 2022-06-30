package com.berk2s.dentist.integration;

import com.berk2s.dentist.infra.adapters.login.controllers.LoginController;
import com.berk2s.dentist.infra.adapters.login.controllers.dtos.LoginRequest;
import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import com.berk2s.dentist.infra.exceptions.ErrorType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

public class LoginIT extends IntegrationTestBase{

    LoginRequest loginRequest;
    UserEntity user;

    @BeforeEach
    void setUp() {
        var password = RandomStringUtils.randomAlphabetic(6);

        user = createUser(password);

        loginRequest = LoginRequest.builder()
                .username(user.getPhoneNumber())
                .password(password)
                .scopes(convertAuthoritiesToString(user.getAuthorities()))
                .build();
    }

    @DisplayName("User should login successfully")
    @Test
    void userShouldLoginSuccessfully() throws Exception {

        mockMvc.perform(post(LoginController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token", matchesPattern("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$")))
                .andExpect(jsonPath("$.refresh_token", hasLength(48)))
                .andExpect(jsonPath("$.expires_in").isNotEmpty());

    }

    @DisplayName("Invalid Request Body Returns Error")
    @Test
    void invalidRequestBodyReturnsError() throws Exception {
        loginRequest.setUsername(null);
        loginRequest.setPassword(null);
        loginRequest.setScopes(null);

        mockMvc.perform(post(LoginController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(ErrorType.INVALID_REQUEST.getType())))
                .andExpect(jsonPath("$.error_description", is(ErrorDesc.INVALID_REQUEST.getDesc())))
                .andExpect(jsonPath("$.code", is(ErrorDesc.INVALID_REQUEST.getCode())));

    }

    @DisplayName("Insufficient Authorities Returns Error")
    @Test
    void insufficientAuthoritiesReturnsError() throws Exception {
        loginRequest.setScopes(RandomStringUtils.randomAlphabetic(4));

        mockMvc.perform(post(LoginController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error", is(ErrorType.INVALID_GRANT.getType())))
                .andExpect(jsonPath("$.error_description", is(ErrorDesc.INSUFFICIENT_AUTHORITY.getDesc())))
                .andExpect(jsonPath("$.code", is(ErrorDesc.INSUFFICIENT_AUTHORITY.getCode())));

    }

    @DisplayName("Invalid passwords Returns Error")
    @Test
    void invalidPasswordsReturnsErrors() throws Exception {
        loginRequest.setPassword(RandomStringUtils.randomAlphabetic(3));

        mockMvc.perform(post(LoginController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error", is(ErrorType.INVALID_GRANT.getType())))
                .andExpect(jsonPath("$.error_description", is(ErrorDesc.INVALID_CREDENTIALS.getDesc())))
                .andExpect(jsonPath("$.code", is(ErrorDesc.INVALID_CREDENTIALS.getCode())));
    }
}
