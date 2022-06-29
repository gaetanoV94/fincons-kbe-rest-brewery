package com.finconsgroup.kberestbrewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {

    @Builder
    public Customer(Long id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerName, Set<BeerOrder> beerOrders) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerName = customerName;
        this.beerOrders = beerOrders;
    }

    private String customerName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BeerOrder> beerOrders;
}
