package com.finconsgroup.kberestbrewery.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderStatusUpdate extends BaseItem {

    @Builder
    public OrderStatusUpdate(Long id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                             Long orderId, String orderStatus, String customerRef) {
        super(id, version, createdDate, lastModifiedDate);
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.customerRef = customerRef;
    }

    private Long orderId;
    private String customerRef;
    private String orderStatus;
}
