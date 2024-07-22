package com.chulos.travelagency.auth.infrastructure.in;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.auth.application.LoginUseCase;
import com.chulos.travelagency.auth.application.RegisterUseCase;
import com.chulos.travelagency.auth.domain.service.AuthService;
import com.chulos.travelagency.auth.infrastructure.out.AuthRepository;
import com.chulos.travelagency.utils.MyUtils;

public class AuthController {
    // attributes
    private final AuthService authService;
    private final LoginView loginView;
    private final RegisterView registerView;
    private final Scanner scanner;

    // controller
    public AuthController(Scanner scanner) {
        // get the repository
        this.authService = new AuthRepository();
        // login
        LoginUseCase loginUseCase = new LoginUseCase(authService);
        this.loginView = new LoginView(loginUseCase, scanner);
        // register
        RegisterUseCase registerUseCase = new RegisterUseCase(authService);
        this.registerView = new RegisterView(registerUseCase, scanner);
        // get scanner
        this.scanner = scanner;
    }

    // run method
    public void start() {
        boolean starApp = true;

        while (starApp == true) {
            try {
                // intro message
                MyUtils.clearScreen();
                System.out.println(  "╔═══════════════════════════════════════════════════════╗\n" +
                                     "║                                                       ║\n" +
                                     "║        ╔╦╗┬─┐┌─┐┬  ┬┌─┐┬    ╔═╗┌─┐┌─┐┌┐┌┌─┐┬ ┬        ║\n" +
                                     "║         ║ ├┬┘├─┤└┐┌┘├┤ │    ╠═╣│ ┬├┤ ││││  └┬┘        ║\n" +
                                     "║         ╩ ┴└─┴ ┴ └┘ └─┘┴─┘  ╩ ╩└─┘└─┘┘└┘└─┘ ┴         ║\n" +
                                     "║                                                       ║\n" +
                                     "║                          ░█                           ║\n" +
                                     "║                         ▄▄█▄▄                         ║\n" +
                                     "║                     ▀▀▀██▀▀▀██▀▀▀                     ║\n" +
                                     "║                 ▄▄▄▄▄▄▄███████▄▄▄▄▄▄▄                 ║\n" +
                                     "║                  ░█▄█  ▀██▄██▀ ░█▄█                   ║\n" +
                                     "║                                                       ║\n" +
                                     "╔═══════════════════════════════════════════════════════╗\n" +
                                     "║                  W E L C O M E   T O                  ║\n" +
                                     "║                T R A V E L A G E N C Y                ║\n" +
                                     "╚═══════════════════════════════════════════════════════╝");

                // menu options
                System.out.println("║                   Choose an option:                   ║");
                System.out.println("╚══════════════════                   ══════════════════╝");
                System.out.println("                     1. Login");
                System.out.println("                     2. Register");
                System.out.println("                     3. Exit");
                System.out.println("╔═══════════════════════════════════════════════════════╗");

                // get the option
                System.out.print("║→");
                int option = scanner.nextInt();
                scanner.nextLine();

                // depends of option show a different menu
                switch (option) {
                    case 1:
                        // login menu
                        MyUtils.clearScreen();
                        loginView.start();
                        break;
                    case 2:
                        // register menu
                        MyUtils.clearScreen();
                        registerView.start();
                        break;
                    case 3:
                        // exit
                        MyUtils.displayMessageAndClearScreen("Exiting the application...", 2);
                        starApp = false;
                        break;
                    default:
                        MyUtils.displayMessageAndClearScreen("Choose a valid option", 2);
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Only numbers are valid", 2);
            } catch (IllegalStateException e) {
                System.out.println("ERROR: Scanner not exists.");
                return;
            } catch (NoSuchElementException e) {
                System.out.println("Error: No more input available");
                return;
            }
        }
    }
}