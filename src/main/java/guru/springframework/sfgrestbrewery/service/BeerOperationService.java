package guru.springframework.sfgrestbrewery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.sfgrestbrewery.enums.BeerOperationEnum;
import guru.springframework.sfgrestbrewery.model.BeerOperation;
import guru.springframework.sfgrestbrewery.repository.BeerOperationRepository;

@Service
public class BeerOperationService {
	
	@Autowired
	private BeerOperationRepository operationRepository;
	
	public void save(int oldCount, int newCount, String from, String to) {
		
		BeerOperationEnum opEnum;
		BeerOperation operation = new BeerOperation();
		
		if(oldCount == newCount) {
			opEnum = BeerOperationEnum.UPDATE;
		}
		else if(oldCount < newCount) {
			opEnum = BeerOperationEnum.INSERT;
		}
		else {
			opEnum = BeerOperationEnum.DELETE;
		}
		
		operation.setOperationEnum(opEnum);
		operation.setBefore(from);
		operation.setAfter(to);
		
		operationRepository.save(operation);
		
	}

}
