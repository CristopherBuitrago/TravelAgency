package com.chulos.travelagency.tripbooking.application;

import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;

public class FindBookingById {
    // get service
    private TripBookingService tripBookingService;

    public FindBookingById(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public TripBooking execute(int id) {
        return tripBookingService.findBookingById(id);
    }
}
