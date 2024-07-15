package com.chulos.travelagency.user.infrastructure.in;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.user.application.CreateUserUseCase;
import com.chulos.travelagency.user.application.DeleteUserUseCase;
import com.chulos.travelagency.user.application.FindUserUseCase;
import com.chulos.travelagency.user.application.GetUsersUseCase;
import com.chulos.travelagency.user.application.UpdateUserUseCase;
import com.chulos.travelagency.user.domain.service.UserService;
import com.chulos.travelagency.user.infrastructure.out.UserRepository;
import com.chulos.travelagency.utils.MyUtils;

public class UserController {
    // attributes
    private final UserService userService;
    private final CreateUserView createUserView;
    private final DeleteUserView deleteUserView;
    private final FindUserView findUserView;
    private final GetUsersView getUsersView;
    private final UpdateUserView updateUserView;

    // constructor
    public UserController() {
        // initialize the repository
        this.userService = new UserRepository();
        // create user
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        this.createUserView = new CreateUserView(createUserUseCase);
        // delete user
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userService);
        this.deleteUserView = new DeleteUserView(deleteUserUseCase);
        // find user
        FindUserUseCase findUserUseCase = new FindUserUseCase(userService);
        this.findUserView = new FindUserView(findUserUseCase);
        // get users
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(userService);
        this.getUsersView = new GetUsersView(getUsersUseCase);
        // update user
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(userService);
        this.updateUserView = new UpdateUserView(updateUserUseCase);
    }

    // run application
    public void run() {
        // define option
        int option;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    // show menu
                    System.out.println("WELCOME TO USERS");
                    System.out.println("1. Create user");
                    System.out.println("2. Update user");
                    System.out.println("3. Find user");
                    System.out.println("4. List users");
                    System.out.println("5. Delete user");
                    System.out.println("6. Exit");
                    System.out.print("[-] ");
                    option = scanner.nextInt(); // catch the response
                    scanner.nextLine(); // clean buffer

                    // depend of the response show different menu
                    switch (option) {
                        case 1:
                            // create menu
                            MyUtils.clearScreen();
                            createUserView.start();
                            break;
                        case 2:
                            // update menu
                            MyUtils.clearScreen();
                            updateUserView.start();
                            break;
                        case 3:
                            // find menu
                            MyUtils.clearScreen();
                            findUserView.start();
                            break;
                        case 4:
                            // list menu
                            MyUtils.clearScreen();
                            getUsersView.start();
                            break;
                        case 5:
                            // delete menu
                            MyUtils.clearScreen();
                            deleteUserView.start();
                            break;
                        case 6:
                            // exit
                            System.out.println("Exiting the application...");
                            scanner.close();
                            return;                            
                        default:
                            // display a message
                            MyUtils.displayMessageAndClearScreen("Error: make sure you chose a valid option", 2);
                    }
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // clean buffer
                    MyUtils.displayMessageAndClearScreen("Error: Only numbers are valid", 2);
                } catch (NoSuchElementException e) {
                    MyUtils.displayMessageAndClearScreen("Error: No more input available", 2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
