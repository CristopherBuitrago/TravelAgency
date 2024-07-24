package com.chulos.travelagency.tripcrew.application;

import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class AddEngineerUseCase {
    // get the service
    private final CrewService crewService;

    public AddEngineerUseCase(CrewService crewService) {
        this.crewService = crewService;
    }

    public String execute(int employeeId, int flightId) {
        return crewService.addTechnical(employeeId, flightId);
    }
}
