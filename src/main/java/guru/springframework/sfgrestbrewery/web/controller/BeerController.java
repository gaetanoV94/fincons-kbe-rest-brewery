package guru.springframework.sfgrestbrewery.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfgrestbrewery.domain.Beer;
import guru.springframework.sfgrestbrewery.domain.BeerStyleEnum;
import guru.springframework.sfgrestbrewery.exception.BeerNotFoundException;
import guru.springframework.sfgrestbrewery.services.BeerService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/v1/")
@RestController
@ResponseBody
@Slf4j
public class BeerController {
	
	@Autowired
	private BeerService beerService;
	
	@GetMapping(value = "beers")
	public ResponseEntity<List<Beer>> getBeers(){
		log.info("Getting all beers from the Database");
		final Iterable<Beer> beerIterable = beerService.getAllBeers();
		final List<Beer> beers = StreamSupport
				.stream(beerIterable.spliterator(), false)
				.collect(Collectors.toList());
		return new ResponseEntity<List<Beer>>(beers, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-beer-from-id/{id}")
	
	public ResponseEntity<Beer> getBeerById(@PathVariable(name = "id") final Integer beerId){
		log.info("Getting beer with beerId {} from the Database.", beerId);
		final Beer beer = beerService.getBeerById(beerId)
				.orElseThrow(() -> new BeerNotFoundException
					("Beer with beerId " + beerId 
							+ " not found in the Database"));
		return new ResponseEntity<Beer>(beer, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-beer-from-name/{beerName}")
	
	public ResponseEntity<List<Beer>> getBeersByBeerName(@PathVariable(name = "beerName") String beerName){
		log.info("Getting beer(s) for name {} from the Database", beerName);
		final List<Beer> beersByName = beerService.findAllByBeerName(beerName);
		return new ResponseEntity<List<Beer>>(beersByName, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-beer-from-beer-style")
	public ResponseEntity<List<Beer>> getBeersFromBeerStyle(@RequestParam("style") BeerStyleEnum beerStyle){
		final Iterable<Beer> beerIterable = beerService.findAllByBeerStyle(beerStyle);
		final List<Beer> beers = StreamSupport
				.stream(beerIterable.spliterator(), false)
				.collect(Collectors.toList());
		return new ResponseEntity<List<Beer>>(beers, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-beer-from-upc/{beerUpc}")
	
	public ResponseEntity<List<Beer>> getBeersFromUpc(@PathVariable(name = "beerUpc") String upc){
		log.info("Getting beer(s) for upc {} from the Database", upc);
		final List<Beer> beerByUpc = beerService.findByUpc(upc);
		return new ResponseEntity<List<Beer>>(beerByUpc, HttpStatus.OK);
	}
	
	@PostMapping(value = "save-new-beer")
	
	public ResponseEntity<Void> saveNewBeer(@RequestBody Beer beer){
		log.info("Saving new beer in the Database");
		beerService.save(beer);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "delete-beer/{beerId}")
	
	public ResponseEntity<Void> deleteBeerById(@PathVariable(name = "beerId") Integer beerId){
		log.info("Deleting beer with beerId {} from the Database", beerId);
		beerService.deleteBeerById(beerId);
		log.info("Record with beerId {} has been deleted", beerId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
