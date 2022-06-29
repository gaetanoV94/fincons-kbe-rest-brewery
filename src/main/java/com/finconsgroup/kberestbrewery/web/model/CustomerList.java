package com.finconsgroup.kberestbrewery.web.model;


import java.util.List;

public class CustomerList {

    private List<CustomerDto> customers;

    public CustomerList(List<CustomerDto> content) {
        this.customers = content;
    }
}
