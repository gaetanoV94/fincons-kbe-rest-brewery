package guru.springframework.sfgrestbrewery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.sfgrestbrewery.domain.Beer;
import guru.springframework.sfgrestbrewery.domain.BeerStyleEnum;
import guru.springframework.sfgrestbrewery.repositories.BeerRepository;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	public void save(final Beer beer) {
		beerRepository.save(beer);
	}
	
	public long getBeerCount() {
		return beerRepository.count();
	}
	
	public Iterable<Beer> getAllBeers(){
		return beerRepository.findAll();
	}
	
	public Optional<Beer> getBeerById(final Integer beerId){
		return beerRepository.findById(beerId);
	}
	
	public List<Beer> findAllByBeerName(String beerName){
		return beerRepository.findAllByBeerName(beerName);
	}
	
	public Iterable<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle){
		return beerRepository.findAllByBeerStyle(beerStyle.name());
	}
	
	public List<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle){
		return beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle);
	}

    public List<Beer> findByUpc(String upc) {
    	return beerRepository.findByUpc(upc);
    }
    
    public void deleteBeerById(Integer beerId) {
    	beerRepository.deleteById(beerId);
    }
}
