package com.finconsgroup.kberestbrewery.repositories;

import com.finconsgroup.kberestbrewery.domain.BeerOrderLine;
import com.finconsgroup.kberestbrewery.domain.BeerOrderLineKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, BeerOrderLineKey> {
}
