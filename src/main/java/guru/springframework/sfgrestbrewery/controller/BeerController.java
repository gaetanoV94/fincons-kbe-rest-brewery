package guru.springframework.sfgrestbrewery.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfgrestbrewery.exception.BeerNotFoundException;
import guru.springframework.sfgrestbrewery.model.Beer;
import guru.springframework.sfgrestbrewery.service.BeerService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/v1/")
@RestController
@ResponseBody
@Slf4j
public class BeerController {
	
	@Autowired
	private BeerService beerService;
	
	@GetMapping(value = "beers")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Beer>> getBeers(){
		log.info("Getting all beers from the Database");
		final Iterable<Beer> beerIterable = beerService.getAllBeers();
		final List<Beer> beers = StreamSupport
				.stream(beerIterable.spliterator(), false)
				.collect(Collectors.toList());
		return new ResponseEntity<List<Beer>>(beers, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-beer-from-id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Beer> getBeerById(@RequestParam(name = "id") final Integer beerId){
		log.info("Getting beer with beerId {} from the Database.", beerId);
		final Beer beer = beerService.getBeerById(beerId)
				.orElseThrow(() -> new BeerNotFoundException
					("Beer with beerId " + beerId 
							+ " not found in the Database"));
		return new ResponseEntity<Beer>(beer, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-beer-from-name/{beerName}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Beer>> getBeersByBeerName(@RequestParam(name = "beerName") String beerName){
		log.info("Getting beer(s) for name {} from the Database", beerName);
		final List<Beer> beersByName = beerService.findAllByBeerName(beerName);
		return new ResponseEntity<List<Beer>>(beersByName, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-beer-from-upc/{beerUpc}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Beer>> getBeersFromUpc(@RequestParam(name = "beerUpc") String upc){
		log.info("Getting beer(s) for upc {} from the Database", upc);
		final List<Beer> beerByUpc = beerService.findByUpc(upc);
		return new ResponseEntity<List<Beer>>(beerByUpc, HttpStatus.OK);
	}
	
	@PostMapping(value = "save-new-beer")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> saveNewBeer(@RequestBody Beer beer){
		log.info("Saving new beer in the Database");
		beerService.save(beer);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "delete-beer/{beerId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteBeerById(@RequestParam(name = "beerId") Integer beerId){
		log.info("Deleting beer with beerId {} from the Database", beerId);
		beerService.deleteBeerById(beerId);
		log.info("Record with beerId {} has been deleted", beerId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
