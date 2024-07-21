package com.chulos.travelagency.tripbooking.application;

import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;

public class DeleteBookingUseCase {
    // get service
    private TripBookingService tripBookingService;

    public DeleteBookingUseCase(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public String execute(int id) {
        return tripBookingService.deleteBooking(id);
    }
}
