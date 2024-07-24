package com.chulos.travelagency.tripcrew.application;

import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class SelectFlightUseCase {
    // get service
    private final CrewService crewService;

    public SelectFlightUseCase(CrewService crewService) {
        this.crewService = crewService;
    }

    public String execute(int flightId){
        return crewService.selectFlight(flightId);
    }
}
