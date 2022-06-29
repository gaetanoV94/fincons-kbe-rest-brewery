package com.finconsgroup.kberestbrewery.web.model;

import com.finconsgroup.kberestbrewery.domain.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDto extends BaseItem {

    @Builder
    public BeerOrderDto(Long id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, Long customerId, List<BeerOrderLineDto> beerOrderLines,
                        OrderStatusEnum orderStatus, String customerRef) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
        this.customerRef = customerRef;
    }

    private Long customerId;
    private String customerRef;
    private List<BeerOrderLineDto> beerOrderLines;
    private OrderStatusEnum orderStatus;
}