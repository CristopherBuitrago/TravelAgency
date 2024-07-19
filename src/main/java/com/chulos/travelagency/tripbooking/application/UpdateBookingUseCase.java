package com.chulos.travelagency.tripbooking.application;

import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;

public class UpdateBookingUseCase {
    // get service
    private TripBookingService tripBookingService;

    public UpdateBookingUseCase(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }
    
    public String execute (TripBooking tripBooking) {
        return tripBookingService.updateBooking(tripBooking);
    }
}
