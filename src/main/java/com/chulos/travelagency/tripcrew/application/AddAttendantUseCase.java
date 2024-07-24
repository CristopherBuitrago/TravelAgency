package com.chulos.travelagency.tripcrew.application;

import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class AddAttendantUseCase {
    // get the service
    private final CrewService crewService;

    public AddAttendantUseCase(CrewService crewService) {
        this.crewService = crewService;
    }

    public String execute(int employeeId, int flightId) {
        return crewService.addAttendant(employeeId, flightId);
    }
}
