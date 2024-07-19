package com.chulos.travelagency.tripbooking.application;

import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;

public class AddBookingUseCase {
    // get the service
    TripBookingService tripBookingService;

    public AddBookingUseCase(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public String execute (TripBooking tripBooking) {
        return tripBookingService.addBooking(tripBooking);
    }
}
