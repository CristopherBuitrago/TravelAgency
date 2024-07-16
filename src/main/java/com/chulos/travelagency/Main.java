package com.chulos.travelagency;

import java.util.Scanner;

import com.chulos.travelagency.customer.application.CreateCustomerUseCase;
import com.chulos.travelagency.customer.domain.service.CustomerService;
import com.chulos.travelagency.customer.infrastructure.in.CreateCustomerView;
import com.chulos.travelagency.customer.infrastructure.out.CustomerRepository;
import com.chulos.travelagency.user.infrastructure.in.UserController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerRepository();

        // test create customer (complete)
        //CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerService);
        //CreateCustomerView createCustomerView = new CreateCustomerView(createCustomerUseCase, scanner);
        //createCustomerView.start();
    }
}