package com.chulos.travelagency.user.infrastructure.in;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.chulos.travelagency.user.application.GetUsersUseCase;
import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.utils.MyUtils;

public class GetUsersView {
    // attributes
    private final GetUsersUseCase getUsersUserCase;

    //constructor
    public GetUsersView(GetUsersUseCase getUsersUserCase) {
        this.getUsersUserCase = getUsersUserCase;
    }

    // start method
    public void start() {
        // get users
        List<User> users = getUsersUserCase.execute();

        // verify if there are users
        if (users != null) {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            int pageSize = 3;
            int totalUsers = users.size();
            int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
            String leftAlignFormat = "| %-4d | %-40s | %-40s | %-35s |%n";

            for (int page = 1; page <= totalPages; page++) {
                System.out.format("+------+------------------------------------------+------------------------------------------+-------------------------------------+%n");
                System.out.format("| ID   | Username                                 | Email                                    | Role                                |%n");
                System.out.format("+------+------------------------------------------+------------------------------------------+-------------------------------------+%n");

                int start = (page - 1) * pageSize;
                int end = Math.min(start + pageSize, totalUsers);
                for (int i = start; i < end; i++) {
                    User user = users.get(i);
                    System.out.format(leftAlignFormat, user.getId(), user.getUsername(), user.getEmail(), user.getRoleName());
                }
                System.out.format("+------+------------------------------------------+------------------------------------------+-------------------------------------+%n");
                System.out.println("                                                                Page " + page + " Of " + totalPages);


                if (page <= totalPages) {
                    System.out.println("                                                          Press enter to continue...                     ");
                    try {
                        System.in.read();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    scanner.nextLine();
                    MyUtils.clearScreen();  
                }
            }
        } else {
            MyUtils.displayMessageAndClearScreen("Ups!, database empty, make sure you have at least one record", 2);
        }
        
    }
}
