package com.berk2s.dentist.infra.adapters.token.entity;

import com.berk2s.dentist.infra.adapters.user.entity.UserEntity;
import com.berk2s.dentist.infra.entities.LongIdentifierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RefreshToken extends LongIdentifierEntity {

    @Column
    private String token;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private UserEntity user;

    @Column
    private LocalDateTime issueTime;

    @Column
    private LocalDateTime notBefore;

    @Column
    private LocalDateTime expiryDateTime;

    @PrePersist
    public void prePersist() {
        if (issueTime != null) {
            issueTime = LocalDateTime.now();
        }
    }

}
