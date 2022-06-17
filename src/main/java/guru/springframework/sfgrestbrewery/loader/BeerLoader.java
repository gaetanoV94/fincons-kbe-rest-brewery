package guru.springframework.sfgrestbrewery.loader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrestbrewery.model.Beer;
import guru.springframework.sfgrestbrewery.enums.BeerStyleEnum;
import guru.springframework.sfgrestbrewery.repository.BeerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BeerLoader implements CommandLineRunner{
	
	public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "9122089364369";
    public static final String BEER_3_UPC = "0083783375213";
    public static final String BEER_4_UPC = "4666337557578";
    public static final String BEER_5_UPC = "8380495518610";
    public static final String BEER_6_UPC = "5677465691934";
    public static final String BEER_7_UPC = "5463533082885";
    public static final String BEER_8_UPC = "5339741428398";
    public static final String BEER_9_UPC = "1726923962766";
    public static final String BEER_10_UPC = "8484957731774";
    public static final String BEER_11_UPC = "6266328524787";
    public static final String BEER_12_UPC = "7490217802727";
    public static final String BEER_13_UPC = "8579613295827";
    public static final String BEER_14_UPC = "2318301340601";
    public static final String BEER_15_UPC = "9401790633828";
    public static final String BEER_16_UPC = "4813896316225";
    public static final String BEER_17_UPC = "3431272499891";
    public static final String BEER_18_UPC = "2380867498485";
    public static final String BEER_19_UPC = "4323950503848";
    public static final String BEER_20_UPC = "4006016803570";
    public static final String BEER_21_UPC = "9883012356263";
    public static final String BEER_22_UPC = "0583668718888";
    public static final String BEER_23_UPC = "9006801347604";
    public static final String BEER_24_UPC = "0610275742736";
    public static final String BEER_25_UPC = "6504219363283";
    public static final String BEER_26_UPC = "7245173761003";
    public static final String BEER_27_UPC = "0326984155094";
    public static final String BEER_28_UPC = "1350188843012";
    public static final String BEER_29_UPC = "0986442492927";
    public static final String BEER_30_UPC = "8670687641074";
    
    private BeerRepository beerRepository;
    private LinkedHashMap<String, Integer> beerMap = new LinkedHashMap<>();
    
    public BeerLoader(BeerRepository repo, Map<String, Integer> map) {
    	this.beerRepository = repo;
    	this.beerMap = (LinkedHashMap<String, Integer>) map;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }
    
    private synchronized void loadBeerObjects() {
        log.debug("Loading initial data. Count is: {}", beerRepository.count() );
        
        Random random = new Random();
        
        if (beerRepository.count() == 0) {

            Beer beer1 = Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle(BeerStyleEnum.ALE)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer1);
            
            beerMap.put(BEER_1_UPC, beer1.hashCode());

            Beer beer2 = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyleEnum.PALE_ALE)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer2);
            
            beerMap.put(BEER_2_UPC, beer2.hashCode());

            Beer beer3 = Beer.builder()
                    .beerName("No Hammers On The Bar")
                    .beerStyle(BeerStyleEnum.WHEAT)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer3);
            
            beerMap.put(BEER_3_UPC, beer3.hashCode());

            Beer beer4 = Beer.builder()
                    .beerName("Blessed")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_4_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer4);
            
            beerMap.put(BEER_4_UPC, beer4.hashCode());

            Beer beer5 = Beer.builder()
                    .beerName("Adjunct Trail")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_5_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer5);
            
            beerMap.put(BEER_5_UPC, beer5.hashCode());

            Beer beer6 = Beer.builder()
                    .beerName("Very GGGreenn")
                    .beerStyle(BeerStyleEnum.IPA)
                    .upc(BEER_6_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer6);
            
            beerMap.put(BEER_6_UPC, beer6.hashCode());

            Beer beer7 = Beer.builder()
                    .beerName("Double Barrel Hunahpu's")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_7_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer7);
            
            beerMap.put(BEER_7_UPC, beer7.hashCode());

            Beer beer8 = Beer.builder()
                    .beerName("Very Hazy")
                    .beerStyle(BeerStyleEnum.IPA)
                    .upc(BEER_8_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer8);
            
            beerMap.put(BEER_8_UPC, beer8.hashCode());

            Beer beer9 = Beer.builder()
                    .beerName("SR-71")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_9_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer9);
            
            beerMap.put(BEER_9_UPC, beer9.hashCode());

            Beer beer10 = Beer.builder()
                    .beerName("Pliny the Younger")
                    .beerStyle(BeerStyleEnum.IPA)
                    .upc(BEER_10_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer10);
            
            beerMap.put(BEER_10_UPC, beer10.hashCode());

            Beer beer11 = Beer.builder()
                    .beerName("Blessed")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_11_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer11);
            
            beerMap.put(BEER_11_UPC, beer11.hashCode());

            Beer beer12 = Beer.builder()
                    .beerName("King Krush")
                    .beerStyle(BeerStyleEnum.IPA)
                    .upc(BEER_12_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer12);
            
            beerMap.put(BEER_12_UPC, beer12.hashCode());

            Beer beer13 = Beer.builder()
                    .beerName("PBS Porter")
                    .beerStyle(BeerStyleEnum.PORTER)
                    .upc(BEER_13_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer13);
            
            beerMap.put(BEER_13_UPC, beer13.hashCode());

            Beer beer14 = Beer.builder()
                    .beerName("Pinball Porter")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_14_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer14);
            
            beerMap.put(BEER_14_UPC, beer14.hashCode());

            Beer beer15 = Beer.builder()
                    .beerName("Golden Budda")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_15_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer15);
            
            beerMap.put(BEER_15_UPC, beer15.hashCode());

            Beer beer16 = Beer.builder()
                    .beerName("Grand Central Red")
                    .beerStyle(BeerStyleEnum.LAGER)
                    .upc(BEER_16_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer16);
            
            beerMap.put(BEER_16_UPC, beer16.hashCode());

            Beer beer17 = Beer.builder()
                    .beerName("Pac-Man")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_17_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer17);
            
            beerMap.put(BEER_17_UPC, beer17.hashCode());

            Beer beer18 = Beer.builder()
                    .beerName("Ro Sham Bo")
                    .beerStyle(BeerStyleEnum.IPA)
                    .upc(BEER_18_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer18);
            
            beerMap.put(BEER_18_UPC, beer18.hashCode());

            Beer beer19 = Beer.builder()
                    .beerName("Summer Wheatly")
                    .beerStyle(BeerStyleEnum.WHEAT)
                    .upc(BEER_19_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer19);
            
            beerMap.put(BEER_19_UPC, beer19.hashCode());

            Beer beer20 = Beer.builder()
                    .beerName("Java Jill")
                    .beerStyle(BeerStyleEnum.LAGER)
                    .upc(BEER_20_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer20);
            
            beerMap.put(BEER_20_UPC, beer20.hashCode());

            Beer beer21 = Beer.builder()
                    .beerName("Bike Trail Pale")
                    .beerStyle(BeerStyleEnum.PALE_ALE)
                    .upc(BEER_21_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer21);
            
            beerMap.put(BEER_21_UPC, beer21.hashCode());

            Beer beer22 = Beer.builder()
                    .beerName("N.Z.P")
                    .beerStyle(BeerStyleEnum.IPA)
                    .upc(BEER_22_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer22);
            
            beerMap.put(BEER_22_UPC, beer22.hashCode());

            Beer beer23 = Beer.builder()
                    .beerName("Stawberry Blond")
                    .beerStyle(BeerStyleEnum.WHEAT)
                    .upc(BEER_23_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer23);
            
            beerMap.put(BEER_23_UPC, beer23.hashCode());

            Beer beer24 = Beer.builder()
                    .beerName("Loco")
                    .beerStyle(BeerStyleEnum.PORTER)
                    .upc(BEER_24_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer24);
            
            beerMap.put(BEER_24_UPC, beer24.hashCode());

            Beer beer25 = Beer.builder()
                    .beerName("Spocktoberfest")
                    .beerStyle(BeerStyleEnum.STOUT)
                    .upc(BEER_25_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer25);
            
            beerMap.put(BEER_25_UPC, beer25.hashCode());

            Beer beer26 = Beer.builder()
                    .beerName("Beach Blond Ale")
                    .beerStyle(BeerStyleEnum.ALE)
                    .upc(BEER_26_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer26);
            
            beerMap.put(BEER_26_UPC, beer26.hashCode());

            Beer beer27 = Beer.builder()
                    .beerName("Bimini Twist IPA")
                    .beerStyle(BeerStyleEnum.IPA)
                    .upc(BEER_27_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer27);
            
            beerMap.put(BEER_27_UPC, beer27.hashCode());

            Beer beer28 = Beer.builder()
                    .beerName("Rod Bender Red Ale")
                    .beerStyle(BeerStyleEnum.ALE)
                    .upc(BEER_28_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer28);
            
            beerMap.put(BEER_28_UPC, beer28.hashCode());

            Beer beer29 = Beer.builder()
                    .beerName("Floating Dock")
                    .beerStyle(BeerStyleEnum.SAISON)
                    .upc(BEER_29_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer29);
            
            beerMap.put(BEER_29_UPC, beer29.hashCode());

            Beer beer30 = Beer.builder()
                    .beerName("El Hefe")
                    .beerStyle(BeerStyleEnum.WHEAT)
                    .upc(BEER_30_UPC)
                    .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2))
                    .quantityOnHand(random.nextInt(5000))
                    .build();
			beerRepository.save(beer30);
            
            beerMap.put(BEER_30_UPC, beer30.hashCode());

            log.debug("Beer Records loaded: {}", beerRepository.count());
        }
    }
    
    public Map<String, Integer> getBeerMap(){
    	return beerMap;
    }

}
