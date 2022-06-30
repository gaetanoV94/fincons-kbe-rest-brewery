package com.finconsgroup.kberestbrewery.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class BeerOrderLineKey implements Serializable {

    @Column(name = "BEER_ORDER_ID")
    private Long beerOrderId;

    @Column(name = "BEER_ID")
    private Long beerId;
}
