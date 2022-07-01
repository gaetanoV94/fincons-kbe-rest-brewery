package com.finconsgroup.kberestbrewery.web.mappers;

import com.finconsgroup.kberestbrewery.domain.Customer;
import com.finconsgroup.kberestbrewery.web.model.CustomerDto;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CustomerMapperDecorator implements CustomerMapper {

    private CustomerMapper customerMapper;

    @Autowired
    @Qualifier("delegate")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {

        CustomerDto dto = customerMapper.customerToCustomerDto(customer);

        dto.setNumberOfOrders(0);
        try {
            if(customer.getBeerOrders() != null && customer.getBeerOrders().size() > 0) {
                dto.setNumberOfOrders(customer.getBeerOrders().size());
            }
        } catch(LazyInitializationException ex) {
            ex.printStackTrace();
        }
        return dto;
    }
}
