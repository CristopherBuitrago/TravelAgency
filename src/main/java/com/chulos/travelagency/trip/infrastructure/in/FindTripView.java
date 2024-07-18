package com.chulos.travelagency.trip.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.trip.application.FindTripUseCase;
import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.utils.MyUtils;

public class FindTripView {
    // get application
    private final FindTripUseCase findTripUseCase;
    private final Scanner scanner;

    public FindTripView(FindTripUseCase findTripUseCase, Scanner scanner) {
        this.findTripUseCase = findTripUseCase;
        this.scanner = scanner;
    }

    public void start() {
        Trip trip = null;
        while (true) {
            try {
                // intro message
                System.out.println("SEARCH TRIP");
                // get data
                int id = MyUtils.getIntInput("Input trip id: ", scanner);
                // find trip
                trip = findTripUseCase.execute(id);

                if (trip != null) {
                    // clear screen
                    MyUtils.clearScreen();
                    // align format
                    String leftAlignFormat = "| %-4d | %-10s | %-20.5f | %-35s |%n";
                    // print head
                    System.out.format("+------+------------+----------------------+-------------------------------------+%n");
                    System.out.format("| ID   | Date       | Price                | flight fare                         |%n");
                    System.out.format("+------+------------+----------------------+-------------------------------------+%n");
                    System.out.format(leftAlignFormat, trip.getId(), trip.getDate(), trip.getPrice(), trip.getFlightFareTitle());
                    System.out.format("+------+------------+----------------------+-------------------------------------+%n");
                    System.out.println("                          Press enter to continue...                                    ");
                    scanner.nextLine();
                    MyUtils.clearScreen();
                    break;
                }   else {
                    MyUtils.displayMessageAndClearScreen("Ups! the trip not exists.", 2);
                    break;
                }

            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! Only numbers are valid.", 2);
            }
        }
    }


}
