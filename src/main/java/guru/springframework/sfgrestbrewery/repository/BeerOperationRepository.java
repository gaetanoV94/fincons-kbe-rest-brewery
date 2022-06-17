package guru.springframework.sfgrestbrewery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrestbrewery.model.BeerOperation;

@Repository
public interface BeerOperationRepository extends JpaRepository<BeerOperation, Integer>{

	
}
