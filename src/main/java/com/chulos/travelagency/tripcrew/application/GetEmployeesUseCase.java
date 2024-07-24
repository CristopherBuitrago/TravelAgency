package com.chulos.travelagency.tripcrew.application;

import java.util.List;

import com.chulos.travelagency.employee.domain.Employee;
import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class GetEmployeesUseCase {
    private final CrewService crewService;

    public GetEmployeesUseCase(CrewService crewService) {
        this.crewService = crewService;
    }

    public List<Employee> execute(int roleType) {
        return crewService.getEmployees(roleType);
    }
}
