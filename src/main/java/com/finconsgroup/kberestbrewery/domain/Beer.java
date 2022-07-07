package com.finconsgroup.kberestbrewery.domain;

import com.finconsgroup.kberestbrewery.web.model.BeerStyleEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Beer extends BaseEntity {

    @Builder
    public Beer(Long id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String beerName,
                BeerStyleEnum beerStyle, String upc, Integer minOnHand,
                Integer quantityToBrew, BigDecimal price, Set<BeerInventory> beerInventory) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.upc = upc;
        this.minOnHand = minOnHand;
        this.quantityToBrew = quantityToBrew;
        this.price = price;
        this.beerInventory = beerInventory;
    }

    private String beerName;

    private BeerStyleEnum beerStyle;

    @Column(unique = true)
    private String upc;

    /**
     * Min on hand qty - used to trigger brew
     */
    private Integer minOnHand;
    private Integer quantityToBrew;
    private BigDecimal price;

    @OneToMany(mappedBy = "beer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BeerInventory> beerInventory = new HashSet<>();
}
