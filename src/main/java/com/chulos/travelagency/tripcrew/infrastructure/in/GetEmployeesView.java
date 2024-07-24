package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.List;

import com.chulos.travelagency.employee.domain.Employee;
import com.chulos.travelagency.tripcrew.application.GetEmployeesUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class GetEmployeesView {
    private final GetEmployeesUseCase getEmployeesUseCase;

    public GetEmployeesView(GetEmployeesUseCase getEmployeesUseCase) {
        this.getEmployeesUseCase = getEmployeesUseCase;
    }

    public void start(int roleType) {
        // get employees
        List<Employee> employees = getEmployeesUseCase.execute(roleType);

        // verify if there are employees
        if (employees != null && !employees.isEmpty()) {
            String leftAlignFormat = "| %-4d | %-40s | %-18s |%n";
            System.out.format("+------+------------------------------------------+--------------------+%n");
            System.out.format("| ID   | Employee                                 | Role               |%n");
            System.out.format("+------+------------------------------------------+--------------------+%n");
            
            // iterate employees list
            for (Employee employee : employees) {
                System.out.format(leftAlignFormat, employee.getId(), employee.getFullName(), employee.getRole());
            }
            System.out.format("+------+------------------------------------------+--------------------+%n");
        } else {
            MyUtils.displayMessageAndClearScreen("Ups! it seems that there are not records yet. Please try again", 3);
        }
    }
}
