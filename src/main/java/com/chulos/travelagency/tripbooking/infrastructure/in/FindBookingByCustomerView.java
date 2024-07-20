package com.chulos.travelagency.tripbooking.infrastructure.in;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.chulos.travelagency.tripbooking.application.FindBookingCustomerUseCase;
import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.utils.MyUtils;

public class FindBookingByCustomerView {
    // use application and use scanner
    private final FindBookingCustomerUseCase findBookingCustomerUseCase;
    private final Scanner scanner;

    // constructor to initialize the application and get the scanner
    public FindBookingByCustomerView(FindBookingCustomerUseCase findBookingCustomerUseCase, Scanner scanner) {
        this.findBookingCustomerUseCase = findBookingCustomerUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        while (true) {
            try {
                // intro message
                System.out.println("FIND TRIP BY CUSTOMER");
                // get data
                int customerId = MyUtils.getIntInput("Input customer's id: ", scanner);
                // execute and save it into a list
                List<TripBooking> bookings = findBookingCustomerUseCase.execute(customerId);
                // verify if there are rows
                if (bookings.size() != 0) {
                    MyUtils.clearScreen(); // clear the screen
                    @SuppressWarnings("resource")
                    Scanner scanner = new Scanner(System.in);
                    int pageSize = 3;
                    int totalBookings = bookings.size();
                    int totalPages = (int) Math.ceil((double) totalBookings / pageSize);
                    String leftAlignFormat = "| %-4d | %-40s | %-10d | %-23.2f | %-10s |%n";

                    for (int page = 1; page <= totalPages; page++) {
                        System.out.format("+------+------------------------------------------+------------+---------------------------+------------+%n");
                        System.out.format("| ID   | Customer                                 | Trip ID    | Payment Amount            | Date       |%n");
                        System.out.format("+------+------------------------------------------+------------+---------------------------+------------+%n");

                        int start = (page - 1) * pageSize;
                        int end = Math.min(start + pageSize, totalBookings);
                        for (int i = start; i < end; i++) {
                            TripBooking tripBooking = bookings.get(i);
                            System.out.format(leftAlignFormat, tripBooking.getId(), tripBooking.getCustomer(), tripBooking.getTripId(), tripBooking.getPayment(), tripBooking.getReservationDate());
                        }
                        System.out.format("+------+------------------------------------------+------------+---------------------------+------------+%n");
                        System.out.println("                                                 Page " + page + " Of " + totalPages);

                        if (page <= totalPages) {
                            System.out.println("                                          Press enter to continue...                     ");
                            try {
                                System.in.read();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            scanner.nextLine();
                            MyUtils.clearScreen();
                        } else {
                            return;
                        }
                    }
                } else {
                    MyUtils.displayMessageAndClearScreen("Ups!, the customer does not have trip bookings yet.",3);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again.", 2);
            }
        }
    }
}
