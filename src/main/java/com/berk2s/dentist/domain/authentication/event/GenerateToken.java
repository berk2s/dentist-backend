package com.berk2s.dentist.domain.authentication.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
public class GenerateToken {

    private UUID userId;
    private List<String> scopes;
    private Duration notBeforeTime;
    private Duration expiryDateTime;
    private Map<String, Object> claims;

}
