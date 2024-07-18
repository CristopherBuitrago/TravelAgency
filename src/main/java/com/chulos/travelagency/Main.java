package com.chulos.travelagency;

import java.util.Scanner;

import com.chulos.travelagency.customer.infrastructure.in.CustomerController;
import com.chulos.travelagency.trip.application.CreateTripUseCase;
import com.chulos.travelagency.trip.domain.service.TripService;
import com.chulos.travelagency.trip.infrastructure.in.CreateTripView;
import com.chulos.travelagency.trip.infrastructure.out.TripRepository;
import com.chulos.travelagency.user.infrastructure.in.UserController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // create trip test individually
        TripService tripService = new TripRepository();
        CreateTripUseCase createTripUseCase = new CreateTripUseCase(tripService);
        CreateTripView createTripView = new CreateTripView(createTripUseCase, scanner);
        createTripView.start();
    }
}