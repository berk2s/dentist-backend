package com.berk2s.dentist.domain.role.usecase;

import com.berk2s.dentist.domain.usecase.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateRole implements UseCase {

    private String roleName;
    private String roleDescription;
}
