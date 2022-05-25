package guru.springframework.sfgrestbrewery.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import guru.springframework.sfgrestbrewery.model.BeerDto;
import guru.springframework.sfgrestbrewery.model.BeerPagedList;
import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;



public interface BeerService {
	
	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String upc);

    void deleteBeerById(UUID beerId);

}
