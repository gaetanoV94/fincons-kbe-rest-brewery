package guru.springframework.sfgrestbrewery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrestbrewery.domain.Beer;
import guru.springframework.sfgrestbrewery.domain.BeerStyleEnum;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer>{
	
	List<Beer> findAllByBeerName(String beerName);
	
	@Query(value = "SELECT * FROM beers WHERE beer_style = :beerStyle", nativeQuery = true)
	Iterable<Beer> findAllByBeerStyle(@Param("beerStyle")String beerStyle);
	
	List<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle);

	List<Beer> findByUpc(String upc);

}
