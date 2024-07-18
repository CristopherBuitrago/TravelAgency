package com.chulos.travelagency.trip.application;

import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.trip.domain.service.TripService;

public class FindTripUseCase {
    // get service
    private final TripService tripService;
    
    public FindTripUseCase(TripService tripService) {
        this.tripService = tripService;
    }

    public Trip execute(int id) {
        return tripService.findTripById(id);
    }
}
