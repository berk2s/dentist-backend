package com.berk2s.dentist.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

@Configuration
public class IDGeneratorConfiguration {

    @Bean
    IdGenerator idGenerator() {
        return new JdkIdGenerator();
    }

}
