package com.chulos.travelagency.customer.application;

import com.chulos.travelagency.customer.domain.entity.Customer;
import com.chulos.travelagency.customer.domain.service.CustomerService;

public class CreateCustomerUseCase {
    // attributes
    private final CustomerService customerService;

    // constructor
    public CreateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    // execute method
    public String execute(Customer customer){
        return customerService.createCustomer(customer);
    }
}
