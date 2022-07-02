package com.berk2s.dentist.domain.authority.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Authority {

    private Long id;
    private String authorityName;
}
