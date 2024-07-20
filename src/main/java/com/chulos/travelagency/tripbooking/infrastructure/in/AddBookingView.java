package com.chulos.travelagency.tripbooking.infrastructure.in;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripbooking.application.AddBookingUseCase;
import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.utils.MyUtils;

public class AddBookingView {
    // use the application and the scanner
    private final AddBookingUseCase addBookingUseCase;
    private final Scanner scanner;

    // constructor to initialize the application and get the scanner
    public AddBookingView(AddBookingUseCase addBookingUseCase, Scanner scanner) {
        this.addBookingUseCase = addBookingUseCase;
        this.scanner = scanner;
    }
    
    // start method
    public void start(){
        while (true) {
            try {
                // intro message
                System.out.println("ADD TRIP BOOKING");
                // get the data
                int customerId = MyUtils.getIntInput("Input customer id: ", scanner);
                int tripId = MyUtils.getIntInput("Input trip id: ", scanner);
                Date reservationDate = MyUtils.getDateInput("Input the reservation date (yyyy-MM-dd): ", scanner);
                // create new trip booking
                TripBooking tripBooking = new TripBooking();
                tripBooking.setCustomerId(customerId);
                tripBooking.setTripId(tripId);
                tripBooking.setReservationDate(reservationDate);
                // add trip booking to database and get the response
                String response = addBookingUseCase.execute(tripBooking);
                // display the response
                MyUtils.displayMessageAndClearScreen(response, 2);
                // exit the application
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            } catch (ParseException e) {
                MyUtils.displayMessageAndClearScreen("Invalid date format. Make sure to utilize format (yyyy-MM-dd).", 3);
            }
        }
    }
}
