package guru.springframework.sfgrestbrewery.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfgrestbrewery.dto.BeerRecord;
import guru.springframework.sfgrestbrewery.exception.BeerNotFoundException;
import guru.springframework.sfgrestbrewery.model.Beer;
import guru.springframework.sfgrestbrewery.enums.BeerStyleEnum;
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
	public CollectionModel<Beer> getBeers(){
		log.info("Getting all beers from the Database");
		final Iterable<Beer> beerIterable = beerService.getAllBeers();
		final List<Beer> beers = StreamSupport
				.stream(beerIterable.spliterator(), false)
				.toList();
		for(Beer beer : beers) {
			Link beerLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(BeerController.class)
							.getBeerById(beer.getId()))
					.withRel("beer-id");
			Link deleteBeerLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(BeerController.class)
							.deleteBeerById(beer.getId()))
					.withRel("delete-beer");
			beer.add(beerLink);
			beer.add(deleteBeerLink);
		}
		
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BeerController.class).getBeers()).withSelfRel();
		
		return CollectionModel.of(beers, link);
	}
	
	@GetMapping(value = "beers/beerId/{beerId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Beer> getBeerById(@PathVariable(name = "beerId") final Integer beerId){
		log.info("Getting beer with beerId {} from the Database.", beerId);
		final Beer beer = beerService.getBeerById(beerId)
				.orElseThrow(() -> new BeerNotFoundException
					("Beer with beerId " + beerId 
							+ " not found in the Database"));
		return new ResponseEntity<>(beer, HttpStatus.OK);
	}
	
	@GetMapping(value = "beers/beers-from-query")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<BeerRecord>> getBeersFromQueryParams(
			@RequestParam(value = "beerName", required = false) String beerName,
			@RequestParam(value = "upc", required = false) String upc,
			@RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle){
		log.info("Getting beer(s) with these values: {}, {}, {} "
				+ "from the Database", beerName, upc, beerStyle);
		final List<BeerRecord> beersByParams = beerService.findBeersByParams(beerName, upc, beerStyle);
		return new ResponseEntity<>(beersByParams, HttpStatus.OK);
	}
	
	@PostMapping(value = "beers/save-new-beer")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> saveNewBeer(@RequestBody BeerRecord beerRecord){
		log.info("Saving new beer in the Database");
		Beer beer = beerService.save(beerRecord);
		Integer beerId = beer.getId();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "beers/beerId/{beerId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteBeerById(@PathVariable(name = "beerId") Integer beerId){
		log.info("Deleting beer with beerId {} from the Database", beerId);
		Beer beer = beerService.getBeerById(beerId)
				.orElseThrow(() -> new BeerNotFoundException
						("Beer with beerId " + beerId 
								+ " not found in the Database"));
		beerService.deleteBeerById(beerId, beer);
		log.info("Record with beerId {} has been deleted", beerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "beers/update-beer")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> updateBeer(
			@RequestParam(value = "beerName") String beerName, 
			@RequestBody BeerRecord beerRecord){
		
		List<BeerRecord> beerByParam = beerService
				.findBeersByParams(beerName, null, null);
		log.info("Beer found: " + beerByParam);
		for (Beer beer : beerService.getAllBeers()) {
			log.info("Hashcode for beer number {}: {}", 
					beer.getId(), beer.hashCode());
			LinkedHashMap<Integer, Integer> mapBeforeUpdate 
				= new LinkedHashMap<>();
			mapBeforeUpdate.put(beer.getId(), 
					Integer.valueOf(beer.hashCode()));
		}
		log.info("Updating beer with beerId {} in the Database", 
				beerByParam.get(0).id());
		
		beerService.updateBeer(beerByParam, beerRecord);
		
		for (Beer beer : beerService.getAllBeers()) {
			log.info("Hashcode for beer number {}: {}", 
					beer.getId(), beer.hashCode());
			LinkedHashMap<Integer, Integer> mapAfterUpdate 
				= new LinkedHashMap<>();
			mapAfterUpdate.put(beer.getId(), 
					Integer.valueOf(beer.hashCode()));
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
