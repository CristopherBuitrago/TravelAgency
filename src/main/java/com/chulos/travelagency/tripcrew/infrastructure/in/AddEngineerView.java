package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripcrew.application.AddEngineerUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class AddEngineerView {
    private final AddEngineerUseCase addEngineerUseCase;
    private final GetEmployeesView getEmployeesView;
    private final Scanner scanner;
    private String response = null;

    public AddEngineerView(GetEmployeesView getEmployeesView, AddEngineerUseCase addEngineerUseCase, Scanner scanner) {
        this.getEmployeesView = getEmployeesView;
        this.addEngineerUseCase = addEngineerUseCase;
        this.scanner = scanner;
    }

    public String start(int flightId) {
        while (true) {
            try {
                getEmployeesView.start(6);
                // intro message
                System.out.println("CHOOSE AN ENGINEER");
                // get data
                int employeeId = MyUtils.getIntInput("Input engineer id: ", scanner);
                // add pilot
                response = addEngineerUseCase.execute(employeeId, flightId);
                // break the cycle
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }
        return response;
    }
}
