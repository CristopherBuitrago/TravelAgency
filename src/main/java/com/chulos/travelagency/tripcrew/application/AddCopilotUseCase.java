package com.chulos.travelagency.tripcrew.application;

import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class AddCopilotUseCase {
    // get the service
    private final CrewService crewService;

    public AddCopilotUseCase(CrewService crewService) {
        this.crewService = crewService;
    }

    public String execute(int employeeId, int flightId) {
        return crewService.addCopilot(employeeId, flightId);
    }
}
