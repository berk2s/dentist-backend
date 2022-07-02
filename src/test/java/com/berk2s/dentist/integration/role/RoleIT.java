package com.berk2s.dentist.integration.role;

import com.berk2s.dentist.infra.adapters.role.controllers.RoleController;
import com.berk2s.dentist.infra.adapters.role.controllers.dtos.CreateRoleRequest;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import com.berk2s.dentist.infra.exceptions.ErrorType;
import com.berk2s.dentist.integration.IntegrationTestBase;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

public class RoleIT extends IntegrationTestBase {

    CreateRoleRequest createRoleRequest;

    @BeforeEach
    void setUp() {
        createRoleRequest = CreateRoleRequest.builder()
                .roleName(RandomStringUtils.randomAlphabetic(8))
                .roleDescription(RandomStringUtils.randomAlphabetic(20))
                .build();
    }

    @DisplayName("Create role successfully")
    @Test
    void createRoleSuccessfully() throws Exception {

        mockMvc.perform(post(RoleController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createRoleRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.roleName", is(createRoleRequest.getRoleName())))
                .andExpect(jsonPath("$.roleDescription", is(createRoleRequest.getRoleDescription())))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.lastModifiedAt").isNotEmpty());
    }

    @DisplayName("Already taken role name returns error")
    @Test
    void alreadyTakenRoleNameReturnsError() throws Exception {
        var role = createRole();

        createRoleRequest.setRoleName(role.getRoleName());
        createRoleRequest.setRoleDescription(role.getRoleDescription());

        mockMvc.perform(post(RoleController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createRoleRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(ErrorType.INVALID_REQUEST.getType())))
                .andExpect(jsonPath("$.error_description", is(ErrorDesc.ROLE_NAME_ALREADY_TAKEN.getDesc())))
                .andExpect(jsonPath("$.code", is(ErrorDesc.ROLE_NAME_ALREADY_TAKEN.getCode())));
    }

    @DisplayName("Invalid request body returns error")
    @Test
    void invalidRequestBodyReturnsError() throws Exception {
        createRoleRequest.setRoleName(null);
        createRoleRequest.setRoleDescription(null);

        mockMvc.perform(post(RoleController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createRoleRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(ErrorType.INVALID_REQUEST.getType())))
                .andExpect(jsonPath("$.error_description", is(ErrorDesc.INVALID_REQUEST.getDesc())))
                .andExpect(jsonPath("$.code", is(ErrorDesc.INVALID_REQUEST.getCode())));
    }

    @DisplayName("Too short role name and description returns error")
    @Test
    void tooShortRoleNameAndDescriptionReturnsError() throws Exception {
        createRoleRequest.setRoleName("A");
        createRoleRequest.setRoleDescription("B");

        mockMvc.perform(post(RoleController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createRoleRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(ErrorType.INVALID_REQUEST.getType())))
                .andExpect(jsonPath("$.error_description", is(ErrorDesc.INVALID_REQUEST.getDesc())))
                .andExpect(jsonPath("$.code", is(ErrorDesc.INVALID_REQUEST.getCode())));
    }

    @DisplayName("Too long role name and description returns error")
    @Test
    void tooLongRoleNameAndDescriptionReturnsError() throws Exception {
        createRoleRequest.setRoleName(RandomStringUtils.randomAlphabetic(101));
        createRoleRequest.setRoleDescription(RandomStringUtils.randomAlphabetic(301));

        mockMvc.perform(post(RoleController.ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createRoleRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(ErrorType.INVALID_REQUEST.getType())))
                .andExpect(jsonPath("$.error_description", is(ErrorDesc.INVALID_REQUEST.getDesc())))
                .andExpect(jsonPath("$.code", is(ErrorDesc.INVALID_REQUEST.getCode())));
    }
}
