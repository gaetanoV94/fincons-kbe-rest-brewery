package com.finconsgroup.kberestbrewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class BeerOrder extends BaseEntity {

    @Builder
    public BeerOrder(Long id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerRef, Customer customer,
                     Set<BeerOrderLine> beerOrderLines, OrderStatusEnum orderStatus) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerRef = customerRef;
        this.customer = customer;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
    }

    private String customerRef;

    @ManyToOne(fetch = FetchType.LAZY) // fare il test con il featch EAGHER durante la lista dei customer
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "beerOrder", cascade = CascadeType.ALL)
    private Set<BeerOrderLine> beerOrderLines;

    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;
}