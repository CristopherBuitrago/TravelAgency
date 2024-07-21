package com.chulos.travelagency.tripbooking.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripbooking.application.AddBookingUseCase;
import com.chulos.travelagency.tripbooking.application.DeleteBookingUseCase;
import com.chulos.travelagency.tripbooking.application.FindBookingByIdUseCase;
import com.chulos.travelagency.tripbooking.application.FindBookingCustomerUseCase;
import com.chulos.travelagency.tripbooking.application.UpdateBookingUseCase;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;
import com.chulos.travelagency.tripbooking.infrastructure.out.TripBookingRepository;
import com.chulos.travelagency.utils.MyUtils;

public class TripBookingController {
    // use the views
    private final AddBookingView addBookingView;
    private final DeleteBookingView deleteBookingView;
    private final FindBookingByCustomerView findBookingByCustomerView;
    private final FindBookingByIdView findBookingByIdView;
    private final UpdateBookingView updateBookingView;
    private final Scanner scanner;

    // constructor
    public TripBookingController (Scanner scanner) {
        // get the scanner
        this.scanner = scanner;
        // get the service
        TripBookingService tripBookingService = new TripBookingRepository();
        // get the applications
        AddBookingUseCase addBookingUseCase = new AddBookingUseCase(tripBookingService);
        DeleteBookingUseCase deleteBookingUseCase = new DeleteBookingUseCase(tripBookingService);
        FindBookingByIdUseCase findBookingByIdUseCase = new FindBookingByIdUseCase(tripBookingService);
        FindBookingCustomerUseCase findBookingCustomerUseCase = new FindBookingCustomerUseCase(tripBookingService);
        UpdateBookingUseCase updateBookingUseCase = new UpdateBookingUseCase(tripBookingService);
        // initialize the views
        this.addBookingView = new AddBookingView(addBookingUseCase, scanner);
        this.deleteBookingView = new DeleteBookingView(deleteBookingUseCase, scanner);
        this.findBookingByCustomerView = new FindBookingByCustomerView(findBookingCustomerUseCase, scanner);
        this.findBookingByIdView = new FindBookingByIdView(findBookingByIdUseCase, scanner);
        this.updateBookingView = new UpdateBookingView(updateBookingUseCase, scanner);
    }

    // run the application
    public void run(){
        while (true) {
            try {
                // show menu and get the option
                int option = showMenu();

                switch (option) {
                    case 1:
                        MyUtils.clearScreen();
                        addBookingView.start();
                        break;
                    case 2:
                        MyUtils.clearScreen();
                        deleteBookingView.start();
                        break;
                    case 3:
                        MyUtils.clearScreen();
                        findBookingByCustomerView.start();
                        break;
                    case 4:
                        MyUtils.clearScreen();
                        findBookingByIdView.start();
                        break;
                    case 5:
                        MyUtils.clearScreen();
                        updateBookingView.start();
                        break;
                    case 6:
                        MyUtils.displayMessageAndClearScreen("Exiting the application...", 2);
                        return;
                    default:
                        MyUtils.displayMessageAndClearScreen("Ups! choose a valid option", 2);
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again.", 2);
            }
        }
    }

    private int showMenu() throws InputMismatchException {
        System.out.println("TRIP MENU");
        System.out.println("1. Add trip booking");
        System.out.println("2. Delete trip booking");
        System.out.println("3. Find trip booking by customer id");
        System.out.println("4. Find trip booking by id");
        System.out.println("5. Update trip booking");
        System.out.println("6. Exit");
        int answer = scanner.nextInt(); 
        scanner.nextLine(); // clear buffet
        return answer;
    }
}
