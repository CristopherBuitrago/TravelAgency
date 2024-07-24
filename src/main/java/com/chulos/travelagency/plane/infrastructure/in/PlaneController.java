package com.chulos.travelagency.plane.infrastructure.in;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.plane.application.CreatePlaneUseCase;
import com.chulos.travelagency.plane.domain.service.PlaneService;
import com.chulos.travelagency.plane.infrastructure.out.PlaneRepository;
import com.chulos.travelagency.utils.MyUtils;

public class PlaneController {
    private final PlaneService planeService;
    private final CreatePlaneView createPlaneView;
    private Scanner scanner;

    // constructor
    public PlaneController(Scanner scanner) {
        // get the repository
        this.planeService = new PlaneRepository();
        // create plane
        CreatePlaneUseCase createPlaneUseCase = new CreatePlaneUseCase(planeService);
        this.createPlaneView = new CreatePlaneView(createPlaneUseCase, scanner);
        // get the scanner
        this.scanner = scanner;
    }

    // run application
    public void run() {
        // define option
        int option;

        while (true) {
            try {
                // show menu
                System.out.println("WELCOME TO PLANES");
                System.out.println("1. Create plane");
                System.out.println("2. Exit");
                System.out.print("[-] ");
                option = scanner.nextInt(); // catch the response
                scanner.nextLine();

                // depend of the response show different menu
                switch (option) {
                    case 1:
                        // create menu
                        MyUtils.clearScreen();
                        createPlaneView.start();
                        break;
                    case 2:
                        System.out.println("Exiting the application...");
                        return;
                    default:
                        // display a message
                        System.out.println("Error: make sure you chose a valid option");
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clean buffer
                MyUtils.displayMessageAndClearScreen("Error: Only numbers are valid", 2);
            } catch (NoSuchElementException e) {
                System.out.println("Error: No more input available");
                return; // Exit the loop if no more input is available
            }
        }
    }
}
