package com.chulos.travelagency.customer.application;

import com.chulos.travelagency.customer.domain.entity.Customer;
import com.chulos.travelagency.customer.domain.service.CustomerService;

public class UpdateCustomerUseCase {
    // attributes
    private final CustomerService customerService;

    // constructor
    public UpdateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    // execute method
    public String execute(Customer customer) {
        return customerService.updateCustomer(customer);
    }
}
