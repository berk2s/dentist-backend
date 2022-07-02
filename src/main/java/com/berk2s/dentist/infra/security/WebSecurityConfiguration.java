package com.berk2s.dentist.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

    /**
     * Configures endpoint security policy
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests((authz) -> authz
                        .antMatchers("/login").permitAll()
                        .antMatchers("/roles").permitAll()
                        .anyRequest().permitAll());

        return http.build();
    }

}
