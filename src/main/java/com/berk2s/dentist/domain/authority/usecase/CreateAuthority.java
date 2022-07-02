package com.berk2s.dentist.domain.authority.usecase;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAuthority {

    private String authorityName;
}
