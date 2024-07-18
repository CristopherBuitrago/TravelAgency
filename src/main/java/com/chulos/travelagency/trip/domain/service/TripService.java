package com.chulos.travelagency.trip.domain.service;

import com.chulos.travelagency.trip.domain.entity.Trip;

public interface TripService {
    String createTrip(Trip trip);
    Trip findTripById(int id);
    String updateTrip(Trip trip);
    String deleteTrip(int id); 
}
