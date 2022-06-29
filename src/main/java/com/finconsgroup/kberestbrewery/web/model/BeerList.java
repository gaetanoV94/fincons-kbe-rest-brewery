package com.finconsgroup.kberestbrewery.web.model;


import java.util.List;

public class BeerList {

    private List<BeerDto> beers;

    public BeerList(List<BeerDto> content) {
        this.beers = content;
    }
}
