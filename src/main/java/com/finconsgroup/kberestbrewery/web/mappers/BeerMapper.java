package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.domain.Beer;
import com.finconsgroup.kberestbrewery.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
