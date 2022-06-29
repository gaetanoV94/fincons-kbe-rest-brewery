package com.finconsgroup.kberestbrewery.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem {

    @Builder
    public BeerOrderLineDto(Long id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                            Long beerId, Integer orderQuantity) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
    }

    private Long beerId;
    private Integer orderQuantity = 0;
}
