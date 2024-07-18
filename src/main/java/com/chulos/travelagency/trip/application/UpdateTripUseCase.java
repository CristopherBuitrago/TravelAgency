package com.chulos.travelagency.trip.application;

import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.trip.domain.service.TripService;

public class UpdateTripUseCase {
    // get service
    private final TripService tripService;

    public UpdateTripUseCase(TripService tripService) {
        this.tripService = tripService;
    }

    public String execute (Trip trip) {
        return tripService.updateTrip(trip);
    }
}
