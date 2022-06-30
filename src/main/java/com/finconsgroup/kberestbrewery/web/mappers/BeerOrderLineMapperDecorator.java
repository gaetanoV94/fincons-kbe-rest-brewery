package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.repositories.BeerOrderRepository;
import com.finconsgroup.kberestbrewery.repositories.BeerRepository;
import com.finconsgroup.kberestbrewery.domain.BeerOrderLine;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BeerRepository beerRepository;
    private BeerOrderRepository beerOrderRepository;

    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerRepository(BeerRepository beerRepository, BeerOrderRepository beerOrderRepository) {
        this.beerRepository = beerRepository;
        this.beerOrderRepository = beerOrderRepository;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        orderLineDto.setBeerId(line.getBeer().getId());
        orderLineDto.setBeerOrderId(line.getBeerOrder().getId());
        return orderLineDto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        BeerOrderLine beerOrderLine = beerOrderLineMapper.dtoToBeerOrderLine(dto);
        beerOrderLine.setBeer(beerRepository.getOne(dto.getBeerId()));
        beerOrderLine.setBeerOrder(beerOrderRepository.getOne(dto.getBeerOrderId()));
        beerOrderLine.setQuantityAllocated(0);
        return beerOrderLine;
    }
}
