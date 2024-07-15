package com.chulos.travelagency;

import com.chulos.travelagency.user.infrastructure.in.UserController;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.run();
    }
}