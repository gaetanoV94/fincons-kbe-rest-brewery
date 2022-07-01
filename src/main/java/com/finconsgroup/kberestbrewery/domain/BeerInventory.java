package com.finconsgroup.kberestbrewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BeerInventory extends BaseEntity {

    @Builder
    public BeerInventory(Long id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, Beer beer,
                         Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.beer = beer;
        this.quantityOnHand = quantityOnHand;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="BEER_ID", referencedColumnName="ID")
    private Beer beer;

    private Integer quantityOnHand = 0;
}
