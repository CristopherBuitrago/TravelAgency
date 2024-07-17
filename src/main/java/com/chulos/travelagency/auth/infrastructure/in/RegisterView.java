package com.chulos.travelagency.auth.infrastructure.in;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.auth.application.RegisterUseCase;
import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.utils.MyUtils;

public class RegisterView {
    // attributes
    private static final int MAX_USERNAME_LENGTH = 40;
    private static final int MAX_EMAIL_LENGTH = 40;
    private static final int MAX_PASSWORD_LENGTH = 40;

    private final RegisterUseCase registerUseCase;
    private final Scanner scanner;

    // constructor
    public RegisterView(RegisterUseCase registerUseCase, Scanner scanner) {
        this.registerUseCase = registerUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        while (true) {
            try {
                // intro message
                System.out.println("Registration");
                String username = getInput("Input username(40): ");
                String email = getInput("Input email(40): ");
                String password = getPassword("Input password(40): ");

                if (isInputValid(username, email, password)) {
                    // create new user
                    User user = new User();
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(password);

                    // register and get response
                    String response = registerUseCase.execute(user);
                    MyUtils.displayMessageAndClearScreen(response, 2);
                    break;
                } else {
                    MyUtils.displayMessageAndClearScreen("Error: One of your answers exceeds the character limit. Try again", 3);
                }

            } catch (NoSuchElementException e) {
                MyUtils.displayMessageAndClearScreen("Ups! not line found.", 2);
                break;
            } catch (IllegalStateException e) {
                MyUtils.displayMessageAndClearScreen("Ups! the scanner is closed.", 2);
                break;
            }
        }
    }

    // get input
    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // get password
    private String getPassword (String prompt) {
        System.out.print(prompt);
        return MyUtils.encryptPassword(MyUtils.readPassword(scanner), 5);
    }

    // is input valid
    private boolean isInputValid (String username, String email, String password) {
        return username.length() <= MAX_USERNAME_LENGTH &&
               email.length() <= MAX_EMAIL_LENGTH &&
               password.length() <= MAX_PASSWORD_LENGTH;
    }
}
