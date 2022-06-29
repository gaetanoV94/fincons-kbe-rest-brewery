package com.finconsgroup.kberestbrewery.repositories;


import com.finconsgroup.kberestbrewery.domain.Beer;
import com.finconsgroup.kberestbrewery.web.model.BeerStyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    List<Beer> findAllByBeerName(String beerName);

    List<Beer> findAllByBeerNameIsLike(String beerName);

    List<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle);

    List<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle);

    Beer findByUpc(String upc);
}
