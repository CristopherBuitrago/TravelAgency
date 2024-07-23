package com.chulos.travelagency;

import java.util.Scanner;

// import com.chulos.travelagency.auth.infrastructure.in.AuthController;
import com.chulos.travelagency.flight.infrastructure.in.FlightController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // AuthController authController = new AuthController(scanner);
        // authController.start();

        // test flight
        FlightController flightController = new FlightController(scanner);
        flightController.run();
    }
}