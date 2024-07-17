package com.chulos.travelagency;

import java.util.Scanner;

import com.chulos.travelagency.auth.infrastructure.in.AuthController;
import com.chulos.travelagency.user.infrastructure.in.UserController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthController authController = new AuthController(scanner);
        authController.start();
    }
}