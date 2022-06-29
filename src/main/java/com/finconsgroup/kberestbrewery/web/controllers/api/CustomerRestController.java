package com.finconsgroup.kberestbrewery.web.controllers.api;

import com.finconsgroup.kberestbrewery.services.CustomerService;
import com.finconsgroup.kberestbrewery.web.model.CustomerDto;
import com.finconsgroup.kberestbrewery.web.model.CustomerList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping(produces = {"application/json"}, path = "customer")
    public ResponseEntity<CustomerList> listCustomers(@RequestParam(value = "customerName", required = false) String customerName) {
        log.debug("Listing Customers");
        CustomerList customerList = customerService.listCustomers(customerName);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping(path = {"customers/{customerId}"}, produces = {"application/json"})
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Long customerId) {
        log.debug("Get Request for CustomerId: " + customerId);
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping(path = "customers")
    ResponseEntity addCustomer(@RequestBody CustomerDto customer) {
        try {
            return new ResponseEntity(customerService.createUser(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(path = {"customers/{customerId}"}, produces = {"application/json"})
    public ResponseEntity updateCustomer(@PathVariable("customerId") Long customerId, @Valid @RequestBody CustomerDto customerDto) {

        customerService.updateCustomer(customerId, customerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"customer/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {

        customerService.deleteById(customerId);
    }

}
