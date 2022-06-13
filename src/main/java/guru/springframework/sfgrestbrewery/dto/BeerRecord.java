package guru.springframework.sfgrestbrewery.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;

public record BeerRecord(
		Integer id, 
		@JsonProperty("version") Long version, 
		@JsonProperty("beerName") String beerName, 
		@JsonProperty("beerStyle") BeerStyleEnum beerStyle, 
		@JsonProperty("upc") String upc, 
		@JsonProperty("quantityOnHand") Integer quantityOnHand, 
		@JsonProperty("price") BigDecimal price, 
		Date createdDate, 
		Date lastModifiedDate){

}
