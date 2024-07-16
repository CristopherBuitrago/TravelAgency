package com.chulos.travelagency.customer.infrastructure.in;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.chulos.travelagency.customer.application.CreateCustomerUseCase;
import com.chulos.travelagency.customer.domain.entity.Customer;
import com.chulos.travelagency.utils.MyUtils;

public class CreateCustomerView {
    private static final int MAX_NAME_LENGTH = 45;
    private static final int MAX_LAST_NAME_LENGTH = 45;
    private static final int MAX_AGE = 100;
    private static final int MAX_DOC_TYPE_LENGTH = 10;
    private static final int MAX_DOC_NUMBER_LENGTH = 20;
    
    private final CreateCustomerUseCase createCustomerUseCase;
    private final Scanner scanner;

    public CreateCustomerView(CreateCustomerUseCase createCustomerUseCase, Scanner scanner) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            try {
                System.out.println("CREATE CUSTOMER");

                String name = getInput("Input customer name: ");
                String lastName = getInput("Input customer last name: ");
                int age = getIntInput("Input customer age: ");
                String docType = getInput("Input doc type (10 characters): ");
                int docNumber = getIntInput("Input doc number: ");

                if (isInputValid(name, lastName, age, docType, docNumber)) {
                    Customer customer = new Customer(name, lastName, age, docType, docNumber);
                    String response = createCustomerUseCase.execute(customer);
                    MyUtils.displayMessageAndClearScreen(response, 2);
                    break;
                } else {
                    MyUtils.displayMessageAndClearScreen("Ups! one of your answers exceeds the character limit. \nPlease try again", 3);
                }

            } catch (InputMismatchException e) {
                scanner.nextLine(); // clean buffer
                MyUtils.displayMessageAndClearScreen("Ups! Only numbers are valid try again", 2);
            } catch (NoSuchElementException e) {
                MyUtils.displayMessageAndClearScreen("Ups! not line found", 2);
                break;
            } catch (IllegalStateException e) {
                MyUtils.displayMessageAndClearScreen("Ups! the scanner is closed", 2);
                break;
            }
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); // clean buffer
        return input;
    }

    private boolean isInputValid(String name, String lastName, int age, String docType, int docNumber) {
        return name.length() <= MAX_NAME_LENGTH && 
               lastName.length() <= MAX_LAST_NAME_LENGTH &&
               age <= MAX_AGE && 
               docType.length() <= MAX_DOC_TYPE_LENGTH &&
               String.valueOf(docNumber).length() <= MAX_DOC_NUMBER_LENGTH;
    }
}
