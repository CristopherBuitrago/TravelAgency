package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripcrew.application.SelectFlightUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class SelectFlightView {
    // get app, flights and scanner
    private final SelectFlightUseCase selectFlightUseCase;
    private final Scanner scanner;
    private String response = null;

    public SelectFlightView(SelectFlightUseCase selectFlightUseCase, Scanner scanner) {
        this.selectFlightUseCase = selectFlightUseCase;
        this.scanner = scanner;
    }

    public String start() {
        while (true) {
            try {
                // intro message
                System.out.println("CHOOSE A FLIGHT");
                // get data
                int flightId = MyUtils.getIntInput("Input the flight id: ", scanner);
                // select flight and get response
                response = selectFlightUseCase.execute(flightId);
                // exit of the cycle
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }

        return response;
    }
}