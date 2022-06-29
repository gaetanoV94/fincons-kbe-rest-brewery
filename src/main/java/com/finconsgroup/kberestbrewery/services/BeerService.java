package com.finconsgroup.kberestbrewery.services;

import com.finconsgroup.kberestbrewery.web.model.BeerDto;
import com.finconsgroup.kberestbrewery.web.model.BeerList;
import com.finconsgroup.kberestbrewery.web.model.BeerStyleEnum;

public interface BeerService {

    BeerList listBeers(String beerName, BeerStyleEnum beerStyle, Boolean showInventoryOnHand);

    BeerDto findBeerById(Long beerId, Boolean showInventoryOnHand);

    BeerDto saveBeer(BeerDto beerDto) throws Exception;

    void updateBeer(Long beerId, BeerDto beerDto);

    void deleteById(Long beerId);

    BeerDto findBeerByUpc(String upc);
}
