package com.chulos.travelagency.user.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.user.application.DeleteUserUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class DeleteUserView {
    // Attributes
    private final DeleteUserUseCase deleteUserUseCase;

    // Constructor
    public DeleteUserView(DeleteUserUseCase deleteUserUseCase) {
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

                    MyUtils.displayMessageAndClearScreen(response, 2);
                    
                    break; // Break the loop after a valid input
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // Clear the invalid input
                    MyUtils.displayMessageAndClearScreen("Error: only numbers are valid.", 2);
                }
            }
        }
    }
}
