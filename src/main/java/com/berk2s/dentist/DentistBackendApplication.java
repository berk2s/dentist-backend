package com.berk2s.dentist;

import com.berk2s.dentist.domain.annotation.DomainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		basePackages = {"com.berk2s"},
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {DomainService.class})
		})
public class DentistBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentistBackendApplication.class, args);
	}

}
