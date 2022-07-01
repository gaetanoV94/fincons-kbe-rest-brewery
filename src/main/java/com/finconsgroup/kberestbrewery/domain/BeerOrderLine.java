package com.finconsgroup.kberestbrewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BeerOrderLine {

    public BeerOrderLine(BeerOrderLineKey key) {
        this.id = key;
    }

    @Builder
    public BeerOrderLine(Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         BeerOrder beerOrder, Beer beer, Integer orderQuantity,
                         Integer quantityAllocated, String upc) {
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.beerOrder = beerOrder;
        this.beer = beer;
        this.orderQuantity = orderQuantity;
        this.quantityAllocated = quantityAllocated;
        this.upc = upc;
    }

    @EmbeddedId
    private BeerOrderLineKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "BEER_ORDER_ID")
    private BeerOrder beerOrder;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "BEER_ID")
    private Beer beer;

    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
    private String upc;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;
}