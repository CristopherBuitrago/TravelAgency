package com.chulos.travelagency.flight.application;

import com.chulos.travelagency.flight.domain.entity.Flight;
import com.chulos.travelagency.flight.domain.service.FlightService;

public class CreateFlightUseCase {
    // get service
    private final FlightService flightService;

    public CreateFlightUseCase(FlightService flightService) {
        this.flightService = flightService;
    }

    public String execute (Flight flight) {
        return flightService.createFlight(flight);
    }
}