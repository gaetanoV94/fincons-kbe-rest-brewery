package com.finconsgroup.kberestbrewery.services;

import com.finconsgroup.kberestbrewery.web.model.CustomerDto;
import com.finconsgroup.kberestbrewery.web.model.CustomerList;

public interface CustomerService {

    CustomerList listCustomers(String customerName);

    CustomerDto findCustomerById(Long customerId);

    Object createUser(CustomerDto customer);

    void deleteById(Long customerId);

    void updateCustomer(Long customerId, CustomerDto customerDto);
}
