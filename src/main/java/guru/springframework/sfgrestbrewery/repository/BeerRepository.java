package guru.springframework.sfgrestbrewery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrestbrewery.model.Beer;
import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer>{

List<Beer> findAllByBeerName(String beerName);
	
	List<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle);
	
	List<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle);

	List<Beer> findByUpc(String upc);
}
