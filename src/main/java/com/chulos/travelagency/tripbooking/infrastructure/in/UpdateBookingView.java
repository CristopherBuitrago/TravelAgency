package com.chulos.travelagency.tripbooking.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripbooking.application.UpdateBookingUseCase;
import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.utils.MyUtils;

public class UpdateBookingView {
    // use application and scanner
    private final UpdateBookingUseCase updateBookingUseCase;
    private final Scanner scanner;

    // constructor to initialize the application and get the scanner
    public UpdateBookingView(UpdateBookingUseCase updateBookingUseCase, Scanner scanner) {
        this.updateBookingUseCase = updateBookingUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        TripBooking tripBooking = new TripBooking();
        while (true) {
            try {
                // intro message
                System.out.println("UPDATE TRIP BOOKING");
                // get data
                int bookingId = MyUtils.getIntInput("Input trip booking id: ", scanner);
                int customerId = MyUtils.getIntInput("Input customer id: ", scanner);
                int tripId = MyUtils.getIntInput("Input new trip id: ", scanner);
                int paymentId = MyUtils.getIntInput("Input new payment id: ", scanner);
                // add values to tripbooking
                tripBooking.setId(bookingId);
                tripBooking.setCustomerId(customerId);
                tripBooking.setTripId(tripId);
                tripBooking.setPaymentId(paymentId);
                // update trip booking and get the response
                String response = updateBookingUseCase.execute(tripBooking);
                // display the response
                MyUtils.displayMessageAndClearScreen(response, 3);
                // exit the view
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again.", 2);
            }
        }
    }
}
