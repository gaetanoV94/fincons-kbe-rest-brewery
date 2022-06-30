package com.finconsgroup.kberestbrewery.web.controllers.api;

import com.finconsgroup.kberestbrewery.services.BeerService;
import com.finconsgroup.kberestbrewery.web.model.BeerDto;
import com.finconsgroup.kberestbrewery.web.model.BeerList;
import com.finconsgroup.kberestbrewery.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BeerRestController {

    private final BeerService beerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "beer")
    public ResponseEntity<BeerList> listBeers(@RequestParam(value = "beerName", required = false) String beerName,
                                              @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                              @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        log.debug("Listing Beers");

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        BeerList beerList = beerService.listBeers(beerName, beerStyle, showInventoryOnHand);

        return new ResponseEntity<BeerList>(beerList, HttpStatus.OK);
    }

    @GetMapping(path = {"beer/{beerId}"}, produces = {"application/json"})
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") Long beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        log.debug("Get Request for BeerId: " + beerId);

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        return new ResponseEntity<>(beerService.findBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping(path = {"beerUpc/{upc}"}, produces = {"application/json"})
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc) {
        return new ResponseEntity<>(beerService.findBeerByUpc(upc), HttpStatus.OK);
    }

    @PostMapping(path = "beer")
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {

        try {
            return new ResponseEntity(beerService.saveBeer(beerDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping(path = {"beer/{beerId}"}, produces = {"application/json"})
    public ResponseEntity updateBeer(@PathVariable("beerId") Long beerId, @Valid @RequestBody BeerDto beerDto) {

        beerService.updateBeer(beerId, beerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"beer/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") Long beerId) {
        beerService.deleteById(beerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<List> badRequestHandler(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath().toString() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
