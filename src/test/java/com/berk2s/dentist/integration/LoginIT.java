package com.berk2s.dentist.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginIT extends IntegrationTestBase{

    @DisplayName("User should login successfully")
    @Test
    void userShouldLoginSuccessfully() throws Exception {

        mockMvc.perform(post("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token", matchesPattern("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$")))
                .andExpect(jsonPath("$.refresh_token", hasLength(48)))
                .andExpect(jsonPath("$.expires_in").isNotEmpty());

    }
}
