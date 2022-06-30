package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.domain.Beer;
import com.finconsgroup.kberestbrewery.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    @Mapping(target = "quantityOnHand", source = "minOnHand")
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(source = "quantityOnHand", target = "minOnHand")
    @Mapping(target = "quantityToBrew", ignore = true)
    @Mapping(target = "beerInventory", ignore = true)
    Beer beerDtoToBeer(BeerDto beerDto);
}
