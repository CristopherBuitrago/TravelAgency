package com.chulos.travelagency.user.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.MyUtils;
import com.chulos.travelagency.user.application.FindUserUseCase;
import com.chulos.travelagency.user.domain.entity.User;

public class FindUserController {
    // attributes
    private final FindUserUseCase findUserUseCase;

    // constructor
    public FindUserController(FindUserUseCase findUserUseCase) {
        this.findUserUseCase = findUserUseCase;
    }

    // start method
    public void start() {
        // attributes
        int id;
        User user = null; 

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    // input message
                    System.out.println("FIND USER");

                    // get user id
                    System.out.print("Input user id: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // clean buffer

                    // get user
                    user = findUserUseCase.execute(id);

                    if (user != null) {
                        // clear screen
                        MyUtils.clearScreen();
                        // align format
                        String leftAlignFormat = "| %-4d | %-40s | %-40s | %-35s |%n";
                        // print head
                        System.out.format("+------+------------------------------------------+------------------------------------------+-------------------------------------+%n");
                        System.out.format("| ID   | Username                                 | Email                                    | Role                                |%n");
                        System.out.format("+------+------------------------------------------+------------------------------------------+-------------------------------------+%n");
                        System.out.format(leftAlignFormat, user.getId(), user.getUsername(), user.getEmail(), user.getRoleName());
                        System.out.format("+------+------------------------------------------+------------------------------------------+-------------------------------------+%n");
                        System.out.println("                                                   Press enter to continue...                                          ");
                        scanner.nextLine();
                        MyUtils.clearScreen();
                        break;
                    }   else {
                        System.out.format("The user with id %d doesn't exist.", id);
                        break;
                    }

                } catch (InputMismatchException e) {
                    scanner.nextLine(); // clean buffer
                    MyUtils.displayMessageAndClearScreen("Error: only numbers are valid.", 2);
                }
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}
