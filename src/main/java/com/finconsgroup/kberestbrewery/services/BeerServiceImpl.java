package com.finconsgroup.kberestbrewery.services;

import com.finconsgroup.kberestbrewery.domain.Beer;
import com.finconsgroup.kberestbrewery.repositories.BeerRepository;
import com.finconsgroup.kberestbrewery.web.mappers.BeerMapper;
import com.finconsgroup.kberestbrewery.web.model.BeerDto;
import com.finconsgroup.kberestbrewery.web.model.BeerList;
import com.finconsgroup.kberestbrewery.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerList listBeers(String beerName, BeerStyleEnum beerStyle, Boolean showInventoryOnHand) {

        log.debug("Listing Beers");

        BeerList beerList;
        List<Beer> beers;

        if (!ObjectUtils.isEmpty(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
            //search both
            beers = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle);
        } else if (!ObjectUtils.isEmpty(beerName) && ObjectUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beers = beerRepository.findAllByBeerName(beerName);
        } else if (ObjectUtils.isEmpty(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beers = beerRepository.findAllByBeerStyle(beerStyle);
        } else {
            beers = beerRepository.findAll();
        }

        if (showInventoryOnHand) {
            beerList = new BeerList(beers
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()));

        } else {
            beerList = new BeerList(beers
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()));
        }
        return beerList;
    }

    @Override
    public BeerDto findBeerById(Long beerId, Boolean showInventoryOnHand) {

        log.debug("Finding Beer by id: " + beerId);

        Optional<Beer> beerOptional = beerRepository.findById(beerId);

        if (beerOptional.isPresent()) {
            log.debug("Found BeerId: " + beerId);
            if (showInventoryOnHand) {
                return beerMapper.beerToBeerDto(beerOptional.get());
            } else {
                return beerMapper.beerToBeerDto(beerOptional.get());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. Id: " + beerId);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRED)
    public BeerDto saveBeer(BeerDto beerDto) throws Exception {
        try {
            return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
        } catch (Exception e) {
            throw new Exception("Error during product creation");
        }
    }

    @Override
    public void updateBeer(Long beerId, BeerDto beerDto) {
        Optional<Beer> beerOptional = beerRepository.findById(beerId);

        beerOptional.ifPresentOrElse(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());
            beerRepository.save(beer);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. Id: " + beerId);
        });
    }

    @Override
    public void deleteById(Long beerId) {
        beerRepository.deleteById(beerId);
    }

    @Override
    public BeerDto findBeerByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
    }
}
