package com.chulos.travelagency.tripbooking.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripbooking.application.FindBookingByIdUseCase;
import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.utils.MyUtils;

public class FindBookingByIdView {
    // use application and use the scanner
    private final FindBookingByIdUseCase findBookingByIdUseCase;
    private final Scanner scanner;

    // constructor to initialize application and get scanner
    public FindBookingByIdView(FindBookingByIdUseCase findBookingByIdUseCase, Scanner scanner) {
        this.findBookingByIdUseCase = findBookingByIdUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        TripBooking tripBooking = null;
        while (true) {
            try {
                // intro message
                System.out.println("FIND TRIP BOOKING BY ID");
                // get id
                int id = MyUtils.getIntInput("Input trip booking id: ", scanner);
                // get the trip
                tripBooking = findBookingByIdUseCase.execute(id);
                // verify if booking is null
                if (tripBooking == null) {
                    MyUtils.displayMessageAndClearScreen("Ups! it seems that the booking doesn't exists.", 3);
                    break; // exit the view
                } else {
                    // clear screen
                    MyUtils.clearScreen();
                    // align format
                    String leftAlignFormat = "| %-4d | %-40s | %-10d | %-23.2f   | %-10s |%n";
                    // print head
                    System.out.format("+------+------------------------------------------+------------+---------------------------+------------+%n");
                    System.out.format("| ID   | Customer                                 | Trip ID    | Payment Amount            | Date       |%n");
                    System.out.format("+------+------------------------------------------+------------+---------------------------+------------+%n");
                    System.out.format(leftAlignFormat, tripBooking.getId(), tripBooking.getCustomer(), tripBooking.getTripId(), tripBooking.getPayment(), tripBooking.getReservationDate());
                    System.out.format("+------+------------------------------------------+------------+---------------------------+------------+%n");
                    System.out.println("                                      Press enter to continue...                                    ");
                    scanner.nextLine();
                    MyUtils.clearScreen();
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again.", 2);
            }
        }
    }
}
