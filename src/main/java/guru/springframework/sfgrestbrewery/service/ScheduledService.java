package guru.springframework.sfgrestbrewery.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String jpaHibernateStatus;
	
	private Map<String, Integer> oldBeerMap = new LinkedHashMap<>();
	
	@Scheduled(cron = "0 */1 * ? * *")
	public void getBeersModifications() throws IOException {
		
		if(jpaHibernateStatus.equals("update")) {
			if(oldBeerMap.isEmpty()) {
				String emptyBeerMapMessage = "Old beer map: " + oldBeerMap + " - " + "The old beer map must be filled before any comparison";
				log.info(emptyBeerMapMessage);
				for(Beer beer : service.getAllBeers()) {
					oldBeerMap.put(beer.getUpc(), beer.hashCode());
				}
				log.info("The old beer map is now ready to be compared");
			}
			else {
				compareBeerMaps(oldBeerMap);
			}
			
		}
		if(jpaHibernateStatus.equals("create-drop")) {
			oldBeerMap = loader.getBeerMap();
			compareBeerMaps(oldBeerMap);
		}
	}
	
	private void compareBeerMaps(Map<String, Integer> oldBeerMap){
		
		Map<String, Integer> newBeerMap = new LinkedHashMap<>();
		for(Beer beer : service.getAllBeers()) {
			newBeerMap.put(beer.getUpc(), beer.hashCode());
		}
		log.info("Old beer map: " + oldBeerMap + "\n" 
				+ "New beer map: " + newBeerMap + "\n");
		areEqualWithArrayValue(oldBeerMap, newBeerMap);
		oldBeerMap = newBeerMap;
		
	}
	
	private void areEqualWithArrayValue(Map<String, Integer> first, Map<String, Integer> second){
        
		if(first.size() > second.size()) {
			HashSet<String> deletedUpcInDB = 
					new HashSet<>(first.keySet());
			deletedUpcInDB.removeAll(second.keySet());
			String deleteMessage = "the upc" 
					+ deletedUpcInDB.toString()
						.replace("[", "")
						.replace("]", "") 
					+ " has been deleted from the Database";
			log.info(deleteMessage);
		}
		else if(first.size() < second.size()) {
			HashSet<String> addedUpcInDB = 
					new HashSet<>(second.keySet());
			addedUpcInDB.removeAll(first.keySet());
			String addMessage = "the upc" 
					+ addedUpcInDB.toString()
						.replace("[", "")
						.replace("]", "") 
					+ " has been added from the Database";
			log.info(addMessage);
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
				String noUpdateMessage = "There were no updates on the DB";
				log.info(noUpdateMessage);
			}
			else {
				String updateMessage = "the upc" 
						+ updatedUpcInDB.toString()
							.replace("[", "")
							.replace("]", "") 
						+ " has been updated in the Database";
				log.info(updateMessage);
			}			
		}
    }

}
