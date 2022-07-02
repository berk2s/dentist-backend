package com.berk2s.dentist.infra.adapters.role.controllers;

import com.berk2s.dentist.domain.role.model.Role;
import com.berk2s.dentist.domain.role.usecase.CreateRole;
import com.berk2s.dentist.domain.usecase.UseCaseHandler;
import com.berk2s.dentist.infra.adapters.role.controllers.dtos.CreateRoleRequest;
import com.berk2s.dentist.infra.adapters.role.controllers.dtos.RoleResponse;
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
@RequestMapping(RoleController.ENDPOINT)
@RestController
public class RoleController {

    public static final String ENDPOINT = "/roles";

    private final UseCaseHandler<Role, CreateRole> createRoleUseCaseHandler;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody CreateRoleRequest createRole) {
        var role = createRoleUseCaseHandler.handle(createRole.toUseCase());

        return new ResponseEntity<>(RoleResponse.fromModel(role), HttpStatus.CREATED);
    }

}
