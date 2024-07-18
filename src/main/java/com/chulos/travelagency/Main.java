package com.chulos.travelagency;

import java.util.Scanner;

import com.chulos.travelagency.customer.infrastructure.in.CustomerController;
import com.chulos.travelagency.trip.application.CreateTripUseCase;
import com.chulos.travelagency.trip.application.DeleteTripUseCase;
import com.chulos.travelagency.trip.application.FindTripUseCase;
import com.chulos.travelagency.trip.application.UpdateTripUseCase;
import com.chulos.travelagency.trip.domain.service.TripService;
import com.chulos.travelagency.trip.infrastructure.in.CreateTripView;
import com.chulos.travelagency.trip.infrastructure.in.DeleteTripView;
import com.chulos.travelagency.trip.infrastructure.in.FindTripView;
import com.chulos.travelagency.trip.infrastructure.in.UpdateTripView;
import com.chulos.travelagency.trip.infrastructure.out.TripRepository;
import com.chulos.travelagency.user.infrastructure.in.UserController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TripService tripService = new TripRepository();

        // create trip test individually
        //CreateTripUseCase createTripUseCase = new CreateTripUseCase(tripService);
        //CreateTripView createTripView = new CreateTripView(createTripUseCase, scanner);
        //createTripView.start();

        // delete trip test individually
        //DeleteTripUseCase deleteTripUseCase = new DeleteTripUseCase(tripService);
        //DeleteTripView deleteTripView = new DeleteTripView(deleteTripUseCase, scanner);
        //deleteTripView.start();

        // find trip test individually
        //FindTripUseCase findTripUseCase = new FindTripUseCase(tripService);
        //FindTripView findTripView = new FindTripView(findTripUseCase, scanner);
        //findTripView.start();

        // update trip test individually
        //UpdateTripUseCase updateTripUseCase = new UpdateTripUseCase(tripService);
        //UpdateTripView updateTripView = new UpdateTripView(updateTripUseCase, scanner);
        //updateTripView.start();
    }
}