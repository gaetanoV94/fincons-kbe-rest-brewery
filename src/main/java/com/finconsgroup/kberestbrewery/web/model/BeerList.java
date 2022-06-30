package com.finconsgroup.kberestbrewery.web.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BeerList {

    private List<BeerDto> beers;

    public BeerList(List<BeerDto> content) {
        this.beers = content;
    }
}
