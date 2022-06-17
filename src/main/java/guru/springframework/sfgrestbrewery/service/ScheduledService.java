package guru.springframework.sfgrestbrewery.service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import guru.springframework.sfgrestbrewery.loader.BeerLoader;
import guru.springframework.sfgrestbrewery.model.Beer;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScheduledService {
	
	@Autowired
	private BeerLoader loader;
	
	@Autowired
	private BeerService service;
	
	@Scheduled(cron = "0 */1 * ? * *")
	public void getBeersModifications() {
		
		Map<String, Integer> oldBeerMap = loader.getBeerMap();
		Map<String, Integer> newBeerMap = new LinkedHashMap<>();
		for(Beer beer : service.getAllBeers()) {
			newBeerMap.put(beer.getUpc(), beer.hashCode());
		}
		areEqualWithArrayValue(oldBeerMap, newBeerMap);
	}
	
	private void areEqualWithArrayValue(Map<String, Integer> first, Map<String, Integer> second) {
        
		if(first.size() > second.size()) {
			HashSet<String> deletedUpcInDB = 
					new HashSet<>(first.keySet());
			deletedUpcInDB.removeAll(second.keySet());
			log.info("the upc {} has been deleted from the Database"
					, deletedUpcInDB.toString().replace("[", "").replace("]", ""));
		}
		else if(first.size() < second.size()) {
			HashSet<String> addedUpcInDB = 
					new HashSet<>(second.keySet());
			addedUpcInDB.removeAll(first.keySet());
			log.info("the upc {} has been added from the Database"
					, addedUpcInDB.toString().replace("[", "").replace("]", ""));
		}
		else if(first.size() == second.size()) {
			
			Map<String, Boolean> mapResult = first.entrySet()
		            .stream()
		            .collect(Collectors.toMap(Entry::getKey, 
		            		e -> e.getValue()
		            		.equals(second.get(e.getKey()))));
			
			Set<String> updatedUpcInDB = mapResult.entrySet()
		              .stream()
		              .filter(entry -> Objects
		            		  .equals(entry.getValue(), 
		            				  Boolean.valueOf(false)))
		              .map(Map.Entry::getKey)
		              .collect(Collectors.toSet());
			
			if(updatedUpcInDB.isEmpty()) {
				log.info("There were no updates on the DB");
			}
			else {
				log.info("The row with upc {} has been updated",
						updatedUpcInDB.toString().replace("[", "").replace("]", ""));
			}
						
		}
    }

}
