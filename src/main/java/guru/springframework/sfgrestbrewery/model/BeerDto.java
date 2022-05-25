package guru.springframework.sfgrestbrewery.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import guru.springframework.sfgrestbrewery.domain.Beer;


public class BeerDto {
	
    private UUID id;

    private String beerName;

    private String beerStyle;

    private String upc;

    private BigDecimal price;

    private Integer quantityOnHand;

    private OffsetDateTime createdDate;
    private OffsetDateTime lastUpdatedDate;
	
    
    
    public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getBeerName() {
		return beerName;
	}



	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}



	public String getBeerStyle() {
		return beerStyle;
	}



	public void setBeerStyle(String beerStyle) {
		this.beerStyle = beerStyle;
	}



	public String getUpc() {
		return upc;
	}



	public void setUpc(String upc) {
		this.upc = upc;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public Integer getQuantityOnHand() {
		return quantityOnHand;
	}



	public void setQuantityOnHand(Integer quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}



	public OffsetDateTime getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(OffsetDateTime createdDate) {
		this.createdDate = createdDate;
	}



	public OffsetDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}



	public void setLastUpdatedDate(OffsetDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}



	public BeerDto() {
		super();
	}



	public BeerDto(UUID id, String beerName, String beerStyle, String upc, BigDecimal price, Integer quantityOnHand,
			OffsetDateTime createdDate, OffsetDateTime lastUpdatedDate) {
		super();
		this.id = id;
		this.beerName = beerName;
		this.beerStyle = beerStyle;
		this.upc = upc;
		this.price = price;
		this.quantityOnHand = quantityOnHand;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	
	public BeerDto(Beer beer) {
		super();
		this.id = beer.getId();
		this.beerName = beer.getBeerName();
		this.beerStyle = beer.getBeerStyle().toString();
		this.upc = beer.getUpc();
		this.price = beer.getPrice();
		this.quantityOnHand = beer.getQuantityOnHand();
		this.createdDate =OffsetDateTime.of(beer.getCreatedDate().toLocalDateTime().getYear(), beer.getCreatedDate().toLocalDateTime().getMonthValue(),
				beer.getCreatedDate().toLocalDateTime().getDayOfMonth(), beer.getCreatedDate().toLocalDateTime().getHour(), beer.getCreatedDate().toLocalDateTime().getMinute(),
				beer.getCreatedDate().toLocalDateTime().getSecond(), beer.getCreatedDate().toLocalDateTime().getNano(), ZoneOffset.UTC); 
		this.lastUpdatedDate = OffsetDateTime.of(beer.getLastModifiedDate().toLocalDateTime().getYear(), beer.getLastModifiedDate().toLocalDateTime().getMonthValue(),
				beer.getLastModifiedDate().toLocalDateTime().getDayOfMonth(), beer.getLastModifiedDate().toLocalDateTime().getHour(), beer.getLastModifiedDate().toLocalDateTime().getMinute(),
				beer.getLastModifiedDate().toLocalDateTime().getSecond(), beer.getLastModifiedDate().toLocalDateTime().getNano(), ZoneOffset.UTC); ;
		
	}

    


}
