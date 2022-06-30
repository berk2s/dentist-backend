package com.berk2s.dentist.domain.user.usecase;

import com.berk2s.dentist.domain.usecase.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthenticateUser implements UseCase {

    private String username;
    private String password;
    private List<String> scopes;
}
