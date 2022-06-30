package com.berk2s.dentist.infra.config;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class TokenDuration {
    Duration lifetime;
}
