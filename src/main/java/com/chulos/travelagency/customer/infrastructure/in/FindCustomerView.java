package com.chulos.travelagency.customer.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.customer.application.FindCustomerUseCase;
import com.chulos.travelagency.customer.domain.entity.Customer;
import com.chulos.travelagency.utils.MyUtils;

public class FindCustomerView {
    // attributes
    private final FindCustomerUseCase findCustomerUseCase;
    private Scanner scanner;

    // constructor
    public FindCustomerView(FindCustomerUseCase findCustomerUseCase, Scanner scanner) {
        this.findCustomerUseCase = findCustomerUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start(){
        Customer customer = null;
        while (true) {
            try {
                // show menu
                System.out.println("FIND CUSTOMER");
                int id = getIntInput("Input customer id: ");

                customer = findCustomerUseCase.execute(id);

                // verify if customer exists
                if (customer != null) {
                    // clear screen
                    MyUtils.clearScreen();
                    // align format
                    String leftAlignFormat = "| %-4d | %-36s | %-36s | %-3d | %-10s | %-20d |%n";
                    // print head
                    System.out.format("+------+--------------------------------------+--------------------------------------+-----+------------+----------------------+%n");
                    System.out.format("| ID   | Name                                 | Last_name                            | Age | Doc_Type   | Doc_Number           |%n");
                    System.out.format("+------+--------------------------------------+--------------------------------------+-----+------------+----------------------+%n");
                    System.out.format(leftAlignFormat, customer.getId(), customer.getName(), customer.getLastName(), customer.getAge(), customer.getDocumentType(), customer.getDocumentNumber());
                    System.out.format("+------+--------------------------------------+--------------------------------------+-----+------------+----------------------+%n");
                    System.out.println("                                                        Press enter to continue...                                          ");
                    scanner.nextLine();
                    MyUtils.clearScreen();
                    
                    break;
                }   else {
                    MyUtils.displayMessageAndClearScreen("the customer not exists.", 2);
                    break;
                }
            } catch (InputMismatchException e) {
                MyUtils.displayMessageAndClearScreen("Ups! Only numbers are valid. Please try again.", 2);
            }
        }
    }

    // method to read an int input
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); // clean buffer
        return input;
    }
}
