package com.chulos.travelagency.flight.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.flight.application.CreateFlightUseCase;
import com.chulos.travelagency.flight.domain.service.FlightService;
import com.chulos.travelagency.flight.infrastructure.out.FlightRepository;
import com.chulos.travelagency.utils.MyUtils;

public class FlightController {
    // get views
    private final CreateFlightView createFlightView;
    private final Scanner scanner;

    public FlightController(Scanner scanner) {
        // get scanner
        this.scanner = scanner;
        // get the FlightService
        FlightService flightService = new FlightRepository();
        // get the application
        CreateFlightUseCase createFlightUseCase = new CreateFlightUseCase(flightService);
        // initialize the view
        this.createFlightView = new CreateFlightView(createFlightUseCase, scanner);
    }

    // run method
    public void run() {
        while (true) {
            try {
                //show menu and get response
                int option = showMenu();
                // switch
                switch (option) {
                    case 1:
                        MyUtils.clearScreen();
                        createFlightView.start();
                        break;
                    case 2:
                        MyUtils.displayMessageAndClearScreen("Exiting the application...", 2);
                        return;
                    default:
                        MyUtils.displayMessageAndClearScreen("Ups! choose a valid option", 2);
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }
    }
    
    // show menu
    private int showMenu() throws InputMismatchException {
        System.out.println("FLIGHTS");
        System.out.println("1. Create flight");
        System.out.println("2. Exit");
        int choose = scanner.nextInt(); 
        scanner.nextLine();
        return choose;
    }
}