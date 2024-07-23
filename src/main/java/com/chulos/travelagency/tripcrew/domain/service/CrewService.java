package com.chulos.travelagency.tripcrew.domain.service;

import java.util.List;

import com.chulos.travelagency.employee.domain.Employee;
import com.chulos.travelagency.flight.domain.entity.Flight;

public interface CrewService {
    List<Employee> getEmployees(int roleType);
    List<Flight> getFlights();
    String selectFlight(int flightId);
    String addPilot(int employeeId, int flightId);
    String addCopilot(int employeeId, int flightId);
    String addAttendant(int employeeId, int flightId);
    String addTechnical(int employeeId, int flightId);
}