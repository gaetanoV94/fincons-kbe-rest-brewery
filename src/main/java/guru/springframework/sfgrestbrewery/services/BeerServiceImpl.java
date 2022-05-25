package guru.springframework.sfgrestbrewery.services;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.springframework.sfgrestbrewery.domain.Beer;
import guru.springframework.sfgrestbrewery.model.BeerDto;
import guru.springframework.sfgrestbrewery.model.BeerPagedList;
import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;
import guru.springframework.sfgrestbrewery.repositories.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepository beerRepository;

	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {

		BeerPagedList beerPagedList;
		Page<Beer> beerPage;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			//search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			//search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			//search beer_service style
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}



		beerPagedList = new BeerPagedList(
				beerPage
				.getContent()
				.stream()
				.map(BeerDto::new)
				.collect(Collectors.toList()),

				PageRequest
				.of(beerPage.getPageable().getPageNumber(),
						beerPage.getPageable().getPageSize()),

				beerPage.getTotalElements());


		return beerPagedList;	
	}

	@Override
	public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeerDto getByUpc(String upc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBeerById(UUID beerId) {
		// TODO Auto-generated method stub

	}

}
