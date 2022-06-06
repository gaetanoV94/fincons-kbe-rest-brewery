package guru.springframework.sfgrestbrewery.dto;

import java.math.BigDecimal;

import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;

public class BeerDTO {

	private Integer id;
	private Long version;
	private String beerName;
	private BeerStyleEnum beerStyle;
	private String upc;
	private Integer quantityOnHand;
	private BigDecimal price;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getBeerName() {
		return beerName;
	}
	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}
	public BeerStyleEnum getBeerStyle() {
		return beerStyle;
	}
	public void setBeerStyle(BeerStyleEnum beerStyle) {
		this.beerStyle = beerStyle;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public Integer getQuantityOnHand() {
		return quantityOnHand;
	}
	public void setQuantityOnHand(Integer quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
