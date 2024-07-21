package com.chulos.travelagency.tripbooking.application;

import java.util.List;

import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;

public class FindBookingCustomerUseCase {
    // get service
    private TripBookingService tripBookingService;

    public FindBookingCustomerUseCase(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public List<TripBooking> execute(int customerId) {
        return tripBookingService.findBookingByCustomer(customerId);
    }
}
