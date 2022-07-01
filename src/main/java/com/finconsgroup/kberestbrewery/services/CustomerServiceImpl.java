package com.finconsgroup.kberestbrewery.services;

import com.finconsgroup.kberestbrewery.domain.Customer;
import com.finconsgroup.kberestbrewery.repositories.CustomerRepository;
import com.finconsgroup.kberestbrewery.web.mappers.CustomerMapper;
import com.finconsgroup.kberestbrewery.web.model.CustomerDto;
import com.finconsgroup.kberestbrewery.web.model.CustomerList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerList listCustomers(String customerName) {
        log.debug("Listing Customers");

        List<Customer> customers = customerRepository.findAll();

        CustomerList customerList = new CustomerList(customers
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList()));
        return customerList;
    }

    @Override
    public CustomerDto findCustomerById(Long customerId) {
        return null;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customer) {
        return null;
    }

    @Override
    public void deleteById(Long customerId) {

    }

    @Override
    public void updateCustomer(Long customerId, CustomerDto customerDto) {

    }
}
