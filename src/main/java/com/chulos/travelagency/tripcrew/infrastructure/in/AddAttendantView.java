package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripcrew.application.AddAttendantUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class AddAttendantView {
    private final AddAttendantUseCase addAttendantUseCase;
    private final GetEmployeesView getEmployeesView;
    private final Scanner scanner;
    private String response = null;

    public AddAttendantView(GetEmployeesView getEmployeesView, AddAttendantUseCase addAttendantUseCase, Scanner scanner) {
        this.getEmployeesView = getEmployeesView;
        this.addAttendantUseCase = addAttendantUseCase;
        this.scanner = scanner;
    }

    public String start(int flightId) {
        while (true) {
            try {
                getEmployeesView.start(4);
                // intro message
                System.out.println("CHOOSE AN ATTENDANT");
                // get data
                int employeeId = MyUtils.getIntInput("Input attendant id: ", scanner);
                // add pilot
                response = addAttendantUseCase.execute(employeeId, flightId);
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
