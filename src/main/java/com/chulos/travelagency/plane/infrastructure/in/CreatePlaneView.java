package com.chulos.travelagency.plane.infrastructure.in;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.plane.application.CreatePlaneUseCase;
import com.chulos.travelagency.plane.domain.entity.Plane;
import com.chulos.travelagency.utils.MyUtils;

public class CreatePlaneView { 
    private static final int MAX_PLATE_NAME = 10;

    // Atributes
    private final CreatePlaneUseCase createPlaneUseCase;
    private final Scanner scanner;

    // Constructor
    public CreatePlaneView(CreatePlaneUseCase createPlaneUseCase, Scanner scanner) {
        this.createPlaneUseCase = createPlaneUseCase;
        this.scanner = scanner;
    }

    // Start method
    public void start() {
        while (true) {
            try {
                System.out.println("CREATE PLANE");

                String plate = getInput("Input plane plate: ");
                int chairs = getIntInput("Input plane last chairs: ");
                int status = getIntInput("Input plane status: ");
                int model = getIntInput("Input model: ");
                LocalDate fabricationDate = MyUtils.getLocalDateInput("Input fabrication date (DD-MM-YYYY): ", scanner);
                int airline = getIntInput("Input airline: ");

                if (isInputValid(plate, chairs, status, model, fabricationDate, airline)) {
                    Plane plane = new Plane(plate, chairs, status, model, fabricationDate, airline);
                    String response = createPlaneUseCase.execute(plane);
                    MyUtils.displayMessageAndClearScreen(response, 2);
                    break;
                } else {
                    MyUtils.displayMessageAndClearScreen("Ups! one of your answers exceeds the character limit. \nPlease try again", 3);
                }

            } catch (InputMismatchException e) {
                scanner.nextLine(); // clean buffer
                MyUtils.displayMessageAndClearScreen("Ups! Only numbers are valid try again", 2);
            } catch (NoSuchElementException e) {
                MyUtils.displayMessageAndClearScreen("Ups! not line found", 2);
                break;
            } catch (IllegalStateException e) {
                MyUtils.displayMessageAndClearScreen("Ups! the scanner is closed", 2);
                break;
            }
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); // clean buffer
        return input;
    }

    private boolean isInputValid(String plate, int chairs, int status, int model, LocalDate fabricationDate, int airline) {
        return plate.length() <= MAX_PLATE_NAME;
    }
}
