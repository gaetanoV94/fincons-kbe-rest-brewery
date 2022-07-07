package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.domain.Customer;
import com.finconsgroup.kberestbrewery.web.model.CustomerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
@DecoratedWith(CustomerMapperDecorator.class)
public interface CustomerMapper {

    @Mapping(source = "customerName", target = "name")
    @Mapping(target = "numberOfOrders", ignore = true)
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(source = "name", target = "customerName")
    @Mapping(target = "beerOrders", ignore = true)
    Customer customerDtoToCustomer(CustomerDto customer);
}
