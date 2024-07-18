package com.chulos.travelagency.trip.application;

import com.chulos.travelagency.trip.domain.service.TripService;

public class DeleteTripUseCase {
    // get service
    private final TripService tripService;

    public DeleteTripUseCase(TripService tripService) {
        this.tripService = tripService;
    }

    public String execute (int id) {
        return tripService.deleteTrip(id);
    }
}
