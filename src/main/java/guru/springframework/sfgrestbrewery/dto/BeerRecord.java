package guru.springframework.sfgrestbrewery.dto;

import java.math.BigDecimal;
import java.util.Date;

import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;

public record BeerRecord(Integer id, Long version, 
		String beerName, BeerStyleEnum beerStyle, String upc, 
		Integer quantityOnHand, BigDecimal price, Date createdDate, Date lastModifiedDate) {

}
