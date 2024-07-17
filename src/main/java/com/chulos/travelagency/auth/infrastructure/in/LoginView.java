package com.chulos.travelagency.auth.infrastructure.in;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.auth.application.LoginUseCase;
import com.chulos.travelagency.auth.domain.entity.Auth;
import com.chulos.travelagency.utils.MyUtils;

public class LoginView {
    // attributes
    private static final int MAX_EMAIL_LENGTH = 40;
    private static final int MAX_PASSWORD_LENGTH = 40;

    private final LoginUseCase loginUseCase;
    private final Scanner scanner;

    // constructor
    public LoginView(LoginUseCase loginUseCase, Scanner scanner) {
        this.loginUseCase = loginUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        while (true) {
            try {
                // intro message
                System.out.println("Login");
                String email = getInput("Input email(40): ");
                String password = getPassword("Input password(40): ");

                // verify if is input valid
               if (isInputValid(email, password)) {
                    // create new user
                    Auth auth = new Auth(email, password);

                    // register and get response
                    String response =loginUseCase.execute(auth);
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
    private boolean isInputValid (String email, String password) {
        return email.length() <= MAX_EMAIL_LENGTH &&
               password.length() <= MAX_PASSWORD_LENGTH;
    }

}
