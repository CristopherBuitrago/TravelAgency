package com.chulos.travelagency.trip.application;

import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.trip.domain.service.TripService;

public class CreateTripUseCase {
    // get the service
    private final TripService tripService;

    public CreateTripUseCase(TripService tripService) {
        this.tripService = tripService;
    }

    public String execute (Trip trip) {
        return tripService.createTrip(trip);
    }
}
