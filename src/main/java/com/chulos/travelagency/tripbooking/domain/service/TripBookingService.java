package com.chulos.travelagency.tripbooking.domain.service;

import java.util.List;

import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;

public interface TripBookingService {
    String addBooking (TripBooking tripBooking);
    String deleteBooking (int id);
    List<TripBooking> findBookingByCustomer(int customerId);
    TripBooking findBookingById (int id);
    String updateBooking (TripBooking tripBooking); 
}