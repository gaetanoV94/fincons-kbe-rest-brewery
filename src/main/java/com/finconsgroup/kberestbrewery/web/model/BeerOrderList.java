package com.finconsgroup.kberestbrewery.web.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BeerOrderList {

    private List<BeerOrderDto> beerOrderDtoList;

    public BeerOrderList(List<BeerOrderDto> list) {
        this.beerOrderDtoList = list;
    }
}
