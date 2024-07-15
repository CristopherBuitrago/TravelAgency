package com.chulos.travelagency.user.infrastructure.in;

import java.util.Scanner;

import com.chulos.travelagency.user.application.CreateUserUseCase;
import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.utils.MyUtils;

public class CreateUserView {
    // Attributes
    private CreateUserUseCase createUserUseCase;
    private Scanner scanner;

    // Constructor
    public CreateUserView(CreateUserUseCase createUserUseCase, Scanner scanner) {
        this.createUserUseCase = createUserUseCase;
        this.scanner = scanner;

    }

    // Start method
    public void start() {
        // Attributes
        String username = null;
        String password = null;
        String email = null;
        String roleCode = null;
        String response;


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
            MyUtils.displayMessageAndClearScreen(response, 2);

            break; // Break the loop
        }
    }
}
