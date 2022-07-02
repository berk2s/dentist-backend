package com.berk2s.dentist.infra.adapters.authority;

import com.berk2s.dentist.domain.authority.model.Authority;
import com.berk2s.dentist.domain.authority.usecase.CreateAuthority;
import com.berk2s.dentist.infra.adapters.authority.entity.AuthorityEntity;
import com.berk2s.dentist.infra.adapters.authority.facade.AuthorityFacade;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorityAdapterTest {

    @Mock
    AuthorityFacade authorityFacade;

    @InjectMocks
    AuthorityAdapter authorityAdapter;

    @DisplayName("Should create authority successfully")
    @Test
    void shouldCreateAuthoritySuccessfully() {
        // Given
        when(authorityFacade.save(any())).thenAnswer(createAuthorityEntity(null));

        var createAuthority = CreateAuthority.builder()
                .authorityName(RandomStringUtils.randomAlphabetic(6))
                .build();

        // When
        var authority = authorityAdapter.create(createAuthority);

        // Then
        assertThat(authority).isNotNull()
                .returns(createAuthority.getAuthorityName(), Authority::getAuthorityName);

        verify(authorityFacade, times(1)).save(any());
    }

    @DisplayName("Should retrieve authority by name successfully")
    @Test
    void shouldRetrieveAuthorityByNameSuccessfully() {
        // Given
        String authorityName = RandomStringUtils.randomAlphabetic(10);
        when(authorityFacade.findByAuthorityName(any())).thenAnswer(createAuthorityEntity(authorityName));

        // When
        var authority = authorityAdapter.retrieveByName(authorityName);

        // Then
        assertThat(authority).isNotNull()
                .returns(authorityName, Authority::getAuthorityName);

        verify(authorityFacade, times(1)).findByAuthorityName(any());
    }

    private Answer<Object> createAuthorityEntity(String name) {
        return i -> {
            if(name != null) {
                var authority = new AuthorityEntity();
                authority.setId(1L);
                authority.setAuthorityName(name);
                authority.setCreatedAt(LocalDateTime.now());
                authority.setLastModifiedAt(LocalDateTime.now());

                return authority;
            }

            var authority = (AuthorityEntity) i.getArguments()[0];
            authority.setId(1L);
            authority.setCreatedAt(LocalDateTime.now());
            authority.setLastModifiedAt(LocalDateTime.now());

            return authority;
        };
    }
}