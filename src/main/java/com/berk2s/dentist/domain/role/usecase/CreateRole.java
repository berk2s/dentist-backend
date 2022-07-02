package com.berk2s.dentist.domain.role.usecase;

import com.berk2s.dentist.domain.usecase.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CreateRole implements UseCase {

    private String roleName;
    private String roleDescription;
    private List<String> authorities;
}
