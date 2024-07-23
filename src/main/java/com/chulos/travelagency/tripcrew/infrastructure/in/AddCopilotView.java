package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripcrew.application.AddCopilotUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class AddCopilotView {
    private final AddCopilotUseCase addCopilotUseCase;
    private final GetEmployeesView getEmployeesView;
    private final Scanner scanner;
    private String response = null;

    public AddCopilotView(GetEmployeesView getEmployeesView, AddCopilotUseCase addCopilotUseCase, Scanner scanner) {
        this.getEmployeesView = getEmployeesView;
        this.addCopilotUseCase = addCopilotUseCase;
        this.scanner = scanner;
    }

    public String start(int flightId) {
        while (true) {
            try {
                getEmployeesView.start(2);
                // intro message
                System.out.println("CHOOSE A COPILOT");
                // get data
                int employeeId = MyUtils.getIntInput("Input copilot id: ", scanner);
                // add pilot
                response = addCopilotUseCase.execute(employeeId, flightId);
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
