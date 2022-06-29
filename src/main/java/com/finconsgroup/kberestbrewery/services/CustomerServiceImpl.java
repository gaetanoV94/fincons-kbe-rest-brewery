package com.finconsgroup.kberestbrewery.services;

import com.finconsgroup.kberestbrewery.repositories.CustomerRepository;
import com.finconsgroup.kberestbrewery.web.model.CustomerDto;
import com.finconsgroup.kberestbrewery.web.model.CustomerList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerList listCustomers(String customerName) {
        return null;
    }

    @Override
    public CustomerDto findCustomerById(Long customerId) {
        return null;
    }

    @Override
    public Object createUser(CustomerDto customer) {
        return null;
    }

    @Override
    public void deleteById(Long customerId) {

    }

    @Override
    public void updateCustomer(Long customerId, CustomerDto customerDto) {

    }
}
