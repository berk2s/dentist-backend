package com.berk2s.dentist.domain.role.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Role {

    private Long id;
    private String roleName;
    private String roleDescription;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
