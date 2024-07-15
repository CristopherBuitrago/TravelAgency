package com.chulos.travelagency.user.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.user.application.UpdateUserUseCase;
import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.utils.MyUtils;

public class UpdateUserView {
    // application use
    private final UpdateUserUseCase updateUserUseCase;

    // constructor
    public UpdateUserView(UpdateUserUseCase updateUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
    }

    // start method
    public void start() {
        // Attributes
        int id;
        String username = null;
        String password = null;
        String email = null;
        String roleCode = null;
        String response;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    // Input message
                    System.out.println("UPDATE USER");

                    // Get id
                    System.out.print("Input user id: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // clean buffer
                    System.out.println();
                    
                    // Get username
                    System.out.print("Input new username (40 characters): ");
                    username = scanner.nextLine();
                    System.out.println();
                    
                    // Get email
                    System.out.print("Input new email (40 characters): ");
                    email = scanner.nextLine();
                    System.out.println();
                    
                    // Get password
                    System.out.print("Input new password (40 characters): ");
                    password = MyUtils.readPassword(scanner);
                    System.out.println();
                    
                    // Get role code
                    System.out.print("Input new role code (10 characters): ");
                    roleCode = scanner.nextLine();
                    System.out.println();

                    // Verify inputs are not out of range
                    if (username.length() > 40 || email.length() > 40 || password.length() > 40 || roleCode.length() > 10) {
                        MyUtils.displayMessageAndClearScreen("Error: Some of your answers exceed the character limit.", 2);
                        continue;
                    }

                    // Create new user
                    User user = new User(id, username, email, password, roleCode);
                    response = updateUserUseCase.execute(user); // Catch response
                    MyUtils.displayMessageAndClearScreen(response, 2);

                    break; // Break the loop
                    
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // clean buffer
                    MyUtils.displayMessageAndClearScreen("ERROR: only numbers are valid.", 2);
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
