package com.chulos.travelagency;

import java.util.Scanner;

import com.chulos.travelagency.trip.infrastructure.in.TripController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TripController tripController = new TripController(scanner);
        tripController.run();
    }
}