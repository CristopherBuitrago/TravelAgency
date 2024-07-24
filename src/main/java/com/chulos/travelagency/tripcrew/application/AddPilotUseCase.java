package com.chulos.travelagency.tripcrew.application;

import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class AddPilotUseCase {
    // get the service
    private final CrewService crewService;

    public AddPilotUseCase(CrewService crewService) {
        this.crewService = crewService;
    }

    public String execute(int employeeId, int flightId) {
        return crewService.addPilot(employeeId, flightId);
    }
}