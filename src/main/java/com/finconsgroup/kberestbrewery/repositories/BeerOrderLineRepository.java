package com.finconsgroup.kberestbrewery.repositories;

import com.finconsgroup.kberestbrewery.domain.BeerOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, Long> {
}
