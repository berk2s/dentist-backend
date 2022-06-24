package com.berk2s.dentist.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginIT extends IntegrationTestBase{

    @DisplayName("User should login successfully")
    @Test
    void userShouldLoginSuccessfully() throws Exception {

        mockMvc.perform(post("/login"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
