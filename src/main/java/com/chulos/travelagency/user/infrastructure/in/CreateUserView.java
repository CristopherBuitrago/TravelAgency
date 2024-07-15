package com.chulos.travelagency.user.infrastructure.in;

import java.util.Scanner;

import com.chulos.travelagency.MyUtils;
import com.chulos.travelagency.user.application.CreateUserUseCase;
import com.chulos.travelagency.user.domain.entity.User;

public class CreateUserView {
    // Attributes
    private CreateUserUseCase createUserUseCase;

    // Constructor
    public CreateUserView(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    // Start method
    public void start() {
        // Attributes
        String username = null;
        String password = null;
        String email = null;
        String roleCode = null;
        String response;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Input message
                System.out.println("CREATE USER");
                
                // Get username
                System.out.print("Input username (40 characters): ");
                username = scanner.nextLine();
                System.out.println();
                
                // Get email
                System.out.print("Input email (40 characters): ");
                email = scanner.nextLine();
                System.out.println();
                
                // Get password
                System.out.print("Input password (40 characters): ");
                password = MyUtils.readPassword(scanner);
                System.out.println();
                
                // Get role code
                System.out.print("Input role code (10 characters): ");
                roleCode = scanner.nextLine();
                System.out.println();

                // Verify inputs are not out of range
                if (username.length() > 40 || email.length() > 40 || password.length() > 40 || roleCode.length() > 10) {
                    MyUtils.displayMessageAndClearScreen("Error: Some of your answers exceed the character limit.", 2);
                    continue;
                }

                // Create new user
                User user = new User(0, username, email, password, roleCode);
                response = createUserUseCase.execute(user); // Catch response
                System.out.println(response);

                break; // Break the loop
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
