package guru.springframework.sfgrestbrewery.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import guru.springframework.sfgrestbrewery.dto.BeerRecord;
import guru.springframework.sfgrestbrewery.enums.BeerStyleEnum;
import guru.springframework.sfgrestbrewery.model.Beer;
import guru.springframework.sfgrestbrewery.repository.BeerRepository;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BeerOperationService beerOperationService;
	
	@Transactional
	public Beer save(BeerRecord beerRecord) {
		
		int oldCount = getBeerCount();
		
		Beer beer = new Beer();
		beer.setVersion(beerRecord.version());
		beer.setBeerName(beerRecord.beerName());
		beer.setBeerStyle(beerRecord.beerStyle());
		beer.setUpc(beerRecord.upc());
		beer.setQuantityOnHand(beerRecord.quantityOnHand());
		beer.setPrice(beerRecord.price());
		
		beer = beerRepository.save(beer);
		
		int newCount = getBeerCount();
		String beerToBeInserted = beer.toString();
		beerOperationService.save(oldCount, newCount, "There was no beer with beerId " + beer.getId(), beerToBeInserted);
		return beer;
	}
	
	public int getBeerCount() {
		return (int) beerRepository.count();
	}
	
	public Iterable<Beer> getAllBeers(){
		return beerRepository.findAll();
	}
	
	public Optional<Beer> getBeerById(final Integer beerId){
		return beerRepository.findById(beerId);
	}
    
    public void deleteBeerById(Integer beerId, Beer beer) {
    	int oldCount = getBeerCount();
    	String beerToBeDeleted = beer.toString();
    	beerRepository.deleteById(beerId);
    	int newCount = getBeerCount();
    	beerOperationService.save(oldCount, newCount, beerToBeDeleted, "Beer with beerId " + beerId + " has been deleted");
    }

	public List<BeerRecord> findBeersByParams(String beerName, String upc, BeerStyleEnum beerStyle) {
		
		String sql = "SELECT * FROM beers WHERE ";
		
		if(beerName != null) {
			sql += "beer_name LIKE '%" + beerName +"%'";
		}
		if(upc != null) {
			sql += "AND upc = " + "'" + upc + "'";
		}
		if(beerStyle != null) {
			sql += "AND beer_style = " + "'" + beerStyle.name() + "'" + ";";
		}
		
		return jdbcTemplate.query(sql, 
				(rs, rowNum) -> new BeerRecord(
						rs.getInt("beer_id"),
						rs.getLong("version"),
						rs.getString("beer_name"),
						BeerStyleEnum.valueOf(rs.getString("beer_style")),
						rs.getString("upc"),
						rs.getInt("quantity_on_hand"),
						rs.getBigDecimal("price"),
						rs.getDate("created_date"),
						rs.getDate("last_modified_date")
				));
	}

	public void updateBeer(List<BeerRecord> beerByParam, BeerRecord beerRecord) {
		
		String oldBeer = "";
		String newBeer = "";
		
		int oldCount = getBeerCount();
		
		Beer beer = new Beer();
		for(BeerRecord rec : beerByParam) {
			beer.setId(rec.id());
			beer.setVersion(rec.version());
			beer.setBeerName(rec.beerName());
			beer.setBeerStyle(rec.beerStyle());
			beer.setUpc(rec.upc());
			beer.setQuantityOnHand(rec.quantityOnHand());
			beer.setPrice(rec.price());
		}
		oldBeer = beer.toString();
		if(beerRecord.version() != null) {
			beer.setVersion(beerRecord.version());
		}
		if(beerRecord.beerName() != null) {
			beer.setBeerName(beerRecord.beerName());
		}
		if(beerRecord.beerStyle() != null) {
			beer.setBeerStyle(beerRecord.beerStyle());
		}
		if(beerRecord.upc() != null) {
			beer.setUpc(beerRecord.upc());
		}
		if(beerRecord.quantityOnHand() != null) {
			beer.setQuantityOnHand(beerRecord.quantityOnHand());
		}
		if(beerRecord.price() != null) {
			beer.setPrice(beerRecord.price());
		}
		int newCount = getBeerCount();
		newBeer = beer.toString();
		beerRepository.save(beer);
		beerOperationService.save(oldCount, newCount, oldBeer, newBeer);
	}

}
