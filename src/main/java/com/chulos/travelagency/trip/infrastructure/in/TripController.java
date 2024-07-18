package com.chulos.travelagency.trip.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.trip.application.CreateTripUseCase;
import com.chulos.travelagency.trip.application.DeleteTripUseCase;
import com.chulos.travelagency.trip.application.FindTripUseCase;
import com.chulos.travelagency.trip.application.UpdateTripUseCase;
import com.chulos.travelagency.trip.domain.service.TripService;
import com.chulos.travelagency.trip.infrastructure.out.TripRepository;
import com.chulos.travelagency.utils.MyUtils;

public class TripController {
    // views
    private final CreateTripView createTripView;
    private final DeleteTripView deleteTripView;
    private final FindTripView findTripView;
    private final UpdateTripView updateTripView;
    private final Scanner scanner;

    // constructor
    public TripController (Scanner scanner) {
        // get the scanner
        this.scanner = scanner;
        // use the service
        TripService tripService = new TripRepository();
        // initialize applications
        CreateTripUseCase createTripUseCase = new CreateTripUseCase(tripService);
        DeleteTripUseCase deleteTripUseCase = new DeleteTripUseCase(tripService);
        FindTripUseCase findTripUseCase = new FindTripUseCase(tripService);
        UpdateTripUseCase updateTripUseCase = new UpdateTripUseCase(tripService);
        // initialize views
        this.createTripView = new CreateTripView(createTripUseCase, scanner);
        this.deleteTripView = new DeleteTripView(deleteTripUseCase, scanner);
        this.findTripView = new FindTripView(findTripUseCase, scanner);
        this.updateTripView = new UpdateTripView(updateTripUseCase, scanner);
    }

    // run
    public void run() {
        while (true) {
            try {
                // show menu and get the option
                int option = showMenu();

                switch (option) {
                    case 1:
                        MyUtils.clearScreen();
                        createTripView.start();
                        break;
                
                    case 2:
                        MyUtils.clearScreen();
                        findTripView.start();
                        break;
                
                    case 3:
                        MyUtils.clearScreen();
                        updateTripView.start();
                        break;
                
                    case 4:
                        MyUtils.clearScreen();
                        deleteTripView.start();                
                        break;
                
                    case 5:
                        MyUtils.displayMessageAndClearScreen("Exiting the application...", 2);
                        return;
                
                    default:
                        MyUtils.displayMessageAndClearScreen("Ups! Choose a valid option.", 2);
                        break;
                }
            } catch (InputMismatchException e) {
                MyUtils.displayMessageAndClearScreen("Ups! Only numbers are valid. Try again", 2);
            }
        }
    }

    // menu
    private int showMenu() throws InputMismatchException {
        System.out.println("TRIP MENU");
        System.out.println("1. Create trip");
        System.out.println("2. Find trip");
        System.out.println("3. Update trip");
        System.out.println("4. Delete trip");
        System.out.println("5. Exit");
        int answer = scanner.nextInt();
        scanner.nextLine(); // clear buffet
        return answer;
    }
}
