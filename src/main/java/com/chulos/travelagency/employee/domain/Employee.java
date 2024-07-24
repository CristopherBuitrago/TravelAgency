package com.chulos.travelagency.employee.domain;

public class Employee {
    // attributes
    private int id;
    private String fullName;
    private String role;
    // full constructor
    public Employee(int id, String fullName, String role) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }
    // empty constructor
    public Employee() {
    }
    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
