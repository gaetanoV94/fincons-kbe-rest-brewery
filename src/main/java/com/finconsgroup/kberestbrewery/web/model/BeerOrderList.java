package com.finconsgroup.kberestbrewery.web.model;

import java.util.List;

public class BeerOrderList {

    private List<BeerOrderDto> beerOrderDtoList;

    public BeerOrderList(List<BeerOrderDto> list) {
        this.beerOrderDtoList = list;
    }
}
