package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripcrew.application.AddPilotUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class AddPilotView {
    private final AddPilotUseCase addPilotUseCase;
    private final GetEmployeesView getEmployeesView;
    private final Scanner scanner;
    private String response = null;

    public AddPilotView(GetEmployeesView getEmployeesView, AddPilotUseCase addPilotUseCase, Scanner scanner) {
        this.getEmployeesView = getEmployeesView;
        this.addPilotUseCase = addPilotUseCase;
        this.scanner = scanner;
    }

    public String start(int flightId) {
        while (true) {
            try {
                getEmployeesView.start(1);
                // intro message
                System.out.println("CHOOSE A PILOT");
                // get data
                int employeeId = MyUtils.getIntInput("Input pilot id: ", scanner);
                // add pilot
                response = addPilotUseCase.execute(employeeId, flightId);
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
