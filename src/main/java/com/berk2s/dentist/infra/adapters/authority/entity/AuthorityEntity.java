package com.berk2s.dentist.infra.adapters.authority.entity;

import com.berk2s.dentist.infra.entities.LongIdentifierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class AuthorityEntity extends LongIdentifierEntity {

    @Column
    private String authorityName;

}
