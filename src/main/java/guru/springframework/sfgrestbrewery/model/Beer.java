package guru.springframework.sfgrestbrewery.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "Beers")
@NoArgsConstructor
@AllArgsConstructor
public class Beer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "beer_name")
    private String beerName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "beer_style")
    private BeerStyleEnum beerStyle;
    
    @Column(name = "upc")
    private String upc;

    @Column(name = "quantity_on_hand")
    private Integer quantityOnHand;
    
    @Column(name = "price")
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

}
