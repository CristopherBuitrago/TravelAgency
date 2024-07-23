package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.chulos.travelagency.flight.domain.entity.Flight;
import com.chulos.travelagency.tripcrew.application.GetFlightsUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class GetFlightsView {
    // get app and scanner
    private final GetFlightsUseCase getFlightsUseCase;
    private final Scanner scanner;

    // constructor
    public GetFlightsView(GetFlightsUseCase getFlightsUseCase, Scanner scanner) {
        this.getFlightsUseCase = getFlightsUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        // get users
        List<Flight> flights = getFlightsUseCase.execute();

        // verify if there are flights
        if (flights != null) {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            int pageSize = 3;
            int totalFlights = flights.size();
            int totalPages = (int) Math.ceil((double) totalFlights / pageSize);
            String leftAlignFormat = "| %-4d | %-17d |%n";

            for (int page = 1; page <= totalPages; page++) {
                System.out.format("+------+-------------------+%n");
                System.out.format("| ID   | Connection Number |%n");
                System.out.format("+------+-------------------+%n");

                int start = (page - 1) * pageSize;
                int end = Math.min(start + pageSize, totalFlights);
                for (int i = start; i < end; i++) {
                    Flight flight = flights.get(i);
                    System.out.format(leftAlignFormat, flight.getId(), flight.getConnectionNumber());
                }
                System.out.format("+------+-------------------+%n");
                System.out.println("      Page " + page + " Of " + totalPages);


                if (page <= totalPages) {
                    System.out.println("  Press enter to continue...");
                    try {
                        System.in.read();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    scanner.nextLine();
                    MyUtils.clearScreen();  
                }
            }
        } else {
            MyUtils.displayMessageAndClearScreen("Ups!, database empty, make sure you have at least one record", 2);
        }
    }
}