package guru.springframework.sfgrestbrewery.bootstrap;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrestbrewery.domain.Beer;
import guru.springframework.sfgrestbrewery.model.BeerStyleEnum;
import guru.springframework.sfgrestbrewery.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {

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

	@Autowired
	private BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}

	private synchronized void loadBeerObjects() {

		if (beerRepository.count() == 0) {

			Random random = new Random();


			beerRepository.save(

					new Beer(
							random.nextLong(),
							"Mango Bobs",
							BeerStyleEnum.ALE,
							BEER_1_UPC,
							random.nextInt(5000),
							new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)
							)
					);
			
			beerRepository.save(

					new Beer(
							random.nextLong(),
							"Telfer Taste",
							BeerStyleEnum.GOSE,
							BEER_2_UPC,
							random.nextInt(5000),
							new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)
							)
					);


		}
	}

}
