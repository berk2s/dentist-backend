package com.berk2s.dentist.infra.adapters.login.controllers.dtos;

import com.berk2s.dentist.domain.authentication.usecase.AuthenticateUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest {

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, max = 200, message = "Username must be between in 3 and 200 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 3, max = 200, message = "Username must be between in 3 and 200 characters")
    private String password;

    @NotEmpty(message = "Scopes must not be empty")
    @Size(min = 1, max = 200, message = "Scopes must be between in 1 and 200 character")
    private String scopes;

    public AuthenticateUser toUseCase() {
        return AuthenticateUser.builder()
                .username(username)
                .password(password)
                .scopes(scopesToList())
                .build();
    }

    private List<String> scopesToList() {
        return Arrays.stream(scopes
                .trim()
                .split(" "))
                .map(i -> i.toUpperCase(Locale.ROOT))
                .collect(Collectors.toList());
    }
}
