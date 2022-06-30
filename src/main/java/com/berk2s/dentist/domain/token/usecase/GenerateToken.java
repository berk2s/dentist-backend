package com.berk2s.dentist.domain.token.usecase;

import com.berk2s.dentist.domain.usecase.UseCase;

import java.util.List;
import java.util.UUID;

public class GenerateToken implements UseCase {

    private UUID userId;
    private List<String> scopes;
}
