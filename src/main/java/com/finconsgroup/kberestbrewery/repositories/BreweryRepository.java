package com.finconsgroup.kberestbrewery.repositories;

import com.finconsgroup.kberestbrewery.domain.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
