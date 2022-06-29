package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.domain.BeerOrder;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    @Mapping(target = "customer", ignore = true)
    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
