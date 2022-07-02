package com.berk2s.dentist.infra.adapters.role.controllers;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import com.berk2s.dentist.infra.adapters.login.controllers.dtos.LoginResponse;
import com.berk2s.dentist.infra.adapters.role.controllers.dtos.CreateRoleRequest;
import com.berk2s.dentist.infra.adapters.role.controllers.dtos.RoleResponse;
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

@Tag(name = "Role", description = "Role operations")
@RequiredArgsConstructor
@RequestMapping(RoleController.ENDPOINT)
@RestController
public class RoleController {

    public static final String ENDPOINT = "/roles";

    private final UseCaseHandler<Role, CreateRole> createRoleUseCaseHandler;

    @Operation(summary = "Create Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class),
                            examples = {
                                    @ExampleObject(name = "Successfully created role", value = SwaggerExample.CREATED_ROLE_RESPONSE)
                            })
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "Bad request", value = SwaggerExample.INVALID_CREATE_ROLE_REQUEST_ERROR),
                                    @ExampleObject(name = "Range error",
                                            description = "Occurs when role name or role description are not in the size range",
                                            value = SwaggerExample.SIZE_CREATE_ROLE_REQUEST_ERROR),
                                    @ExampleObject(name = "Taken role name",
                                            description = "Occurs when a role name is already taken by other role",
                                            value = SwaggerExample.ROLE_NAME_ALREADY_TAKEN_ERROR)
                            })
            }),
            @ApiResponse(responseCode = "401", description = "Unauthenticated", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "403", description = "Unauthorized", content = {
                    @Content()
            }),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody CreateRoleRequest createRole) {
        var role = createRoleUseCaseHandler.handle(createRole.toUseCase());

        return new ResponseEntity<>(RoleResponse.fromModel(role), HttpStatus.CREATED);
    }

}
