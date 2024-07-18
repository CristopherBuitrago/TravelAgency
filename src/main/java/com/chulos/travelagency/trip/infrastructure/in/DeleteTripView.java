package com.chulos.travelagency.trip.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.trip.application.DeleteTripUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class DeleteTripView {
    // get application
    private final DeleteTripUseCase deleteTripUseCase;
    private final Scanner scanner;

    // constructor
    public DeleteTripView(DeleteTripUseCase deleteTripUseCase, Scanner scanner) {
        this.deleteTripUseCase = deleteTripUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        while (true) {
            try {
                // intro message
                System.out.println("DELETE TRIP");
                // get data
                int id = getIntInput("Input trip id: ");
                // delete trip and get response
                String response = deleteTripUseCase.execute(id);
                // display response
                MyUtils.displayMessageAndClearScreen(response, 2);
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! Only numbers are valid. Please try again", 2);
            }
        }
    }

    private int getIntInput (String prompt) throws InputMismatchException{
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return input;
    }
}
