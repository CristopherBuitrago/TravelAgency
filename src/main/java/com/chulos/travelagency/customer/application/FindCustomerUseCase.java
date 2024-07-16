package com.chulos.travelagency.customer.application;

import com.chulos.travelagency.customer.domain.entity.Customer;
import com.chulos.travelagency.customer.domain.service.CustomerService;

public class FindCustomerUseCase {
    // attributes
    private final CustomerService customerService;

    // constructor
    public FindCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    // execute method
    public Customer findUserById(int id) {
        return customerService.findCustomerById(id);
    }
}
