package com.chulos.travelagency.customer.infrastructure.in;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.customer.application.CreateCustomerUseCase;
import com.chulos.travelagency.customer.application.FindCustomerUseCase;
import com.chulos.travelagency.customer.application.UpdateCustomerUseCase;
import com.chulos.travelagency.customer.domain.service.CustomerService;
import com.chulos.travelagency.customer.infrastructure.out.CustomerRepository;
import com.chulos.travelagency.utils.MyUtils;

public class CustomerController {
    // attributes
    private final CustomerService customerService;
    private final CreateCustomerView createCustomerView;
    private final FindCustomerView findCustomerView;
    private final UpdateCustomerView updateCustomerView;
    private Scanner scanner;

    // constructor
    public CustomerController (Scanner scanner) {
        // get scanner
        this.scanner = scanner;
        // initialize the repository
        this.customerService = new CustomerRepository();
        // create customer
        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerService);
        this.createCustomerView = new CreateCustomerView(createCustomerUseCase, scanner);
        // find user
        FindCustomerUseCase findCustomerUseCase = new FindCustomerUseCase(customerService);
        this.findCustomerView = new FindCustomerView(findCustomerUseCase, scanner);
        // update user
        UpdateCustomerUseCase updateCustomerUseCase = new UpdateCustomerUseCase(customerService);
        this.updateCustomerView = new UpdateCustomerView(updateCustomerUseCase, scanner);
    }

    // run application
    public void run() {
        // define option
        int option;

        while (true) {
            try {
                // show menu
                System.out.println("WELCOME TO CUSTOMERS");
                System.out.println("1. Create customer");
                System.out.println("2. Update customer");
                System.out.println("3. Find customer");
                System.out.println("4. Exit");
                System.out.print("[-] ");
                option = scanner.nextInt(); // catch the response
                scanner.nextLine();

                // depend of the response show different menu
                switch (option) {
                    case 1:
                        // create menu
                        MyUtils.clearScreen();
                        createCustomerView.start();
                        break;
                    case 2:
                        // update menu
                        MyUtils.clearScreen();
                        updateCustomerView.start();
                        break;
                    case 3:
                        // find menu
                        MyUtils.clearScreen();
                        findCustomerView.start();
                        break;
                    case 4:
                        MyUtils.displayMessageAndClearScreen("Exiting the application...", 2);
                        return;                      
                    default:
                        // display a message
                        MyUtils.displayMessageAndClearScreen("Error: make sure you chose a valid option", 2);
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clean buffer
                MyUtils.displayMessageAndClearScreen("Error: Only numbers are valid", 2);
            } catch (NoSuchElementException e) {
                System.out.println("Error: No more input available");
                return; // Exit the loop if no more input is available
            }
        }
    }
}
