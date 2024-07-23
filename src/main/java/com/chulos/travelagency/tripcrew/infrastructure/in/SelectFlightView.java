package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripcrew.application.SelectFlightUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class SelectFlightView {
    // get app, flights and scanner
    private final SelectFlightUseCase selectFlightUseCase;
    private final GetFlightsView getFlightsView;
    private final Scanner scanner;
    private String response = null;

    public SelectFlightView(GetFlightsView getFlightsView, SelectFlightUseCase selectFlightUseCase, Scanner scanner) {
        this.getFlightsView = getFlightsView;
        this.selectFlightUseCase = selectFlightUseCase;
        this.scanner = scanner;
    }

    public int start() {
        int flightId = 0;
        while (true) {
            try {
                boolean isEmpty = getFlightsView.start();
                // verify if the table is empty
                if (isEmpty) {
                    flightId = 0;
                    break;
                }
                // intro message
                System.out.println("CHOOSE A FLIGHT");
                // get data
                flightId = MyUtils.getIntInput("Input the flight id: ", scanner);
                // select flight and get response
                response = selectFlightUseCase.execute(flightId);
                // show the message
                MyUtils.displayMessageAndClearScreen(response, 3);

                if ("Ups! it seems that the flight does not available. Please try again".equals(response)) {
                    flightId = 0;
                } 
                // exit of the cycle
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }

        return flightId;
    }
}