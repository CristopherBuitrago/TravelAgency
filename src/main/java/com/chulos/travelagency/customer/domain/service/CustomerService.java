package com.chulos.travelagency.customer.domain.service;

import com.chulos.travelagency.customer.domain.entity.Customer;

public interface CustomerService {
       String createCustomer (Customer customer);
       Customer findCustomerById (int id);
       String updateCustomer (Customer customer);
}
