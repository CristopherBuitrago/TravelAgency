package com.chulos.travelagency.tripbooking.domain.service;

import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;

public interface TripBookingService {
    String addTripReservation(TripBooking tripBooking);
    String deleteTripBooking(int id);
    String updateTripBooking(TripBooking tripBooking);
    TripBooking findTripBooking(int id_customer);
}