package com.chulos.travelagency.tripbooking.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripbooking.application.DeleteBookingUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class DeleteBookingView {
    // use application and scanner
    private final DeleteBookingUseCase deleteBookingUseCase;
    private final Scanner scanner;

    // initialize application and get scanner
    public DeleteBookingView(DeleteBookingUseCase deleteBookingUseCase, Scanner scanner) {
        this.deleteBookingUseCase = deleteBookingUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        while (true) {
            try {
                // intro message
                System.out.println("DELETE TRIP BOOKING");
                // get data
                int id = MyUtils.getIntInput("Input trip booking id: ", scanner);
                // delete trip and get response
                String response = deleteBookingUseCase.execute(id);
                // display response
                MyUtils.displayMessageAndClearScreen(response, 2);
                // exit to the view
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 0);
            }
        }
    }
}