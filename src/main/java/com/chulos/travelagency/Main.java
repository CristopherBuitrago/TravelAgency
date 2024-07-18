package com.chulos.travelagency;

import java.util.Scanner;

import com.chulos.travelagency.customer.infrastructure.in.CustomerController;
import com.chulos.travelagency.user.infrastructure.in.UserController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerController customerController = new CustomerController(scanner);
        customerController.run();
    }
}