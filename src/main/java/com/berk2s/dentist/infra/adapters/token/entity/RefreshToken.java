package com.berk2s.dentist.infra.adapters.token.entity;

import com.berk2s.dentist.infra.entities.LongIdentifierEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RefreshToken extends LongIdentifierEntity {

    @Column
    private String token;

    @Column
    private LocalDateTime issueTime;

    @Column
    private LocalDateTime notBefore;

    @Column
    private LocalDateTime expiryDateTime;

}
