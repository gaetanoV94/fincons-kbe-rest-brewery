package com.finconsgroup.kberestbrewery.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem {

    @Builder
    public CustomerDto(Long id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String name) {
        super(id, version, createdDate, lastModifiedDate);
        this.name = name;
    }

    private String name;
}
