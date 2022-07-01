package com.finconsgroup.kberestbrewery.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class BeerOrderLineDto {

    @Builder
    public BeerOrderLineDto(Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                            Long beerId, String upc, Integer orderQuantity) {
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.beerId = beerId;
        this.upc = upc;
        this.orderQuantity = orderQuantity;
    }

    private Long beerId;
    private String upc;
    private Long beerOrderId;
    private Integer orderQuantity = 0;

    @JsonProperty("version")
    private Integer version = null;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate = null;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @JsonProperty("lastModifiedDate")
    private OffsetDateTime lastModifiedDate = null;
}
