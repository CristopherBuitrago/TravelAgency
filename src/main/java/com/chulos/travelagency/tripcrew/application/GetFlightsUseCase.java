package com.chulos.travelagency.tripcrew.application;

import java.util.List;

import com.chulos.travelagency.flight.domain.entity.Flight;
import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class GetFlightsUseCase {
    // get the service
    private final CrewService crewService;

    // constructor
    public GetFlightsUseCase(CrewService crewService) {
        this.crewService = crewService;
    }

    // execute method
    public List<Flight> execute() {
        return crewService.getFlights();
    } 
}
