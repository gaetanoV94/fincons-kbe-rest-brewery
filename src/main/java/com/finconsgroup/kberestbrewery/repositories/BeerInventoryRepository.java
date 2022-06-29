package com.finconsgroup.kberestbrewery.repositories;

import com.finconsgroup.kberestbrewery.domain.Beer;
import com.finconsgroup.kberestbrewery.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerInventoryRepository extends JpaRepository<BeerInventory, Long> {

    List<BeerInventory> findAllByBeer(Beer beer);
}
