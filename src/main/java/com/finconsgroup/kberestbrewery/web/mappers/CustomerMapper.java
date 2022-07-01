package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.domain.Customer;
import com.finconsgroup.kberestbrewery.web.model.CustomerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(CustomerMapperDecorator.class)
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);
}
