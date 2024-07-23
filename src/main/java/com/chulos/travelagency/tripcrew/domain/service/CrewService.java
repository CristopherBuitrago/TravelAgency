package com.chulos.travelagency.tripcrew.domain.service;

import java.util.List;

import com.chulos.travelagency.flight.domain.entity.Flight;

public interface CrewService {
    List<Flight> getFlights();
    String selectFlight(int flightId);
    String addPilot(int employeeId);
    String addCopilot(int employeeId);
    String addAttendant(int employeeId);
    String addTechnical(int employeeId);
}