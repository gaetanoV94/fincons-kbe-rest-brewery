package com.finconsgroup.kberestbrewery.web.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerList {

    private List<CustomerDto> customers;

    public CustomerList(List<CustomerDto> content) {
        this.customers = content;
    }
}
