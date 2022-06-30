package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.domain.BeerOrderLine;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderLineDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {

    @Mapping(target = "beerId", ignore = true)
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    @Mapping(target = "beerOrder", ignore = true)
    @Mapping(target = "beer", ignore = true)
    @Mapping(target = "quantityAllocated", ignore = true)
    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
