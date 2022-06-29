package com.finconsgroup.kberestbrewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Brewery extends BaseEntity {

    @Builder
    public Brewery(Long id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String breweryName) {
        super(id, version, createdDate, lastModifiedDate);
        this.breweryName = breweryName;
    }

    private String breweryName;

}
