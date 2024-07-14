package com.chulos.travelagency.user.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.MyUtils;
import com.chulos.travelagency.user.application.DeleteUserUseCase;

public class DeleteUserController {
    // Attributes
    private final DeleteUserUseCase deleteUserUseCase;

    // Constructor
    public DeleteUserController(DeleteUserUseCase deleteUserUseCase) {
        this.deleteUserUseCase = deleteUserUseCase;
    }

    // Start method
    public void start() {
        // Attributes
        int id;
        String response;

        // Try to get a valid number
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    // Input message
                    System.out.println("DELETE USER");
                    // Get id
                    System.out.print("Input user id: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Clean the buffer
                    System.out.println(); // Change of line

                    // Delete user and get response
                    response = deleteUserUseCase.execute(id);

                    // Handle responses
                    if (response == null) {
                        System.out.format("The user with id %d doesn't exist.%n", id);
                    } else {
                        System.out.println(response);
                    }
                    break; // Break the loop after a valid input
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // Clear the invalid input
                    MyUtils.displayMessageAndClearScreen("Error: only numbers are valid.", 2);
                }
            }
        }
    }
}
