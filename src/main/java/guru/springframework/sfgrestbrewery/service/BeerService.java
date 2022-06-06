package guru.springframework.sfgrestbrewery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import guru.springframework.sfgrestbrewery.dto.BeerDTO;
import guru.springframework.sfgrestbrewery.model.Beer;
import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;
import guru.springframework.sfgrestbrewery.repository.BeerRepository;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(BeerDTO beerDto) {
		
		Beer beer = new Beer();
		beer.setVersion(beerDto.getVersion());
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle());
		beer.setUpc(beerDto.getUpc());
		beer.setQuantityOnHand(beerDto.getQuantityOnHand());
		beer.setPrice(beerDto.getPrice());
		
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
    
    public void deleteBeerById(Integer beerId) {
    	beerRepository.deleteById(beerId);
    }

	public List<Beer> findBeersByParams(String beerName, String upc, BeerStyleEnum beerStyle) {
		
		String sql = "SELECT * FROM beers WHERE ";
		
		if(beerName != null) {
			sql += "beer_name LIKE '%" + beerName +"%' AND ";
		}
		if(upc != null) {
			sql += "upc = " + "'" + upc + "'" + " AND ";
		}
		if(beerStyle != null) {
			sql += "beer_style = " + "'" + beerStyle.name() + "'" + ";";
		}
		
		return jdbcTemplate.query(sql, 
				(rs, rowNum) -> new Beer(
						rs.getInt("id"),
						rs.getLong("version"),
						rs.getString("beer_name"),
						BeerStyleEnum.valueOf(rs.getString("beer_style")),
						rs.getString("upc"),
						rs.getInt("quantity_on_hand"),
						rs.getBigDecimal("price"),
						rs.getTimestamp("created_date"),
						rs.getTimestamp("last_modified_date")
						
				));
	}

}
