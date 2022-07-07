package com.finconsgroup.kberestbrewery.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class BeerOrderLineKey implements Serializable {

    @Column(name = "BEER_ORDER_ID")
    private Long beerOrderId;

    @Column(name = "BEER_ID")
    private Long beerId;

    public BeerOrderLineKey() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BeerOrderLineKey that = (BeerOrderLineKey) o;
        return Objects.equals(beerOrderId, that.beerOrderId) && Objects.equals(beerId, that.beerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beerOrderId, beerId);
    }
}
