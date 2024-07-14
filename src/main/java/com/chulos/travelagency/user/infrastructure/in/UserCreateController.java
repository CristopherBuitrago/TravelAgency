package com.chulos.travelagency.user.infrastructure.in;

import java.net.Socket;
import java.util.Scanner;

import com.chulos.travelagency.MyUtils;
import com.chulos.travelagency.user.application.CreateUserUseCase;
import com.chulos.travelagency.user.domain.entity.User;

public class UserCreateController {
    // attributes
    private CreateUserUseCase createUserUseCase;

    // constructor
    public UserCreateController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    // start method
    public void start() {
        // attributes
        String username = null;
        String password = null;
        String email = null;
        String roleCode = null;
        String response;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // input message
                System.out.println("CREATE USER");
                // get username
                System.out.print("Input username (40 characters): ");
                username = scanner.nextLine(); // catch the value
                System.out.println();
                // get email
                System.out.print("Input email (40 characters): ");
                email = scanner.nextLine();
                System.out.println();
                // get password
                System.out.print("Input password (40 characters): ");
                password = MyUtils.readPassword(scanner);
                System.out.println();
                // get role
                System.out.print("Input role code (10 characters)");
                roleCode = scanner.nextLine();
                System.out.println();

                // verify inputs are not out of range
                if (username.length() > 40 || email.length() > 40 || password.length() > 40 || roleCode.length() > 10) {
                    MyUtils.displayMessageAndClearScreen("Error: Some of your answers exceed the character limit.", 2);
                    continue;
                }
                
                // create new user
                User user = new User(0, username, email, password, roleCode);
                createUserUseCase.execute(user);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
