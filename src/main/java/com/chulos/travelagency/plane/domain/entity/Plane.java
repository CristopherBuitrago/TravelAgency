package com.chulos.travelagency.plane.domain.entity;

import java.time.LocalDate;

public class Plane {
    private String plate;
    private int chairs;
    private int status;
    private int model;
    private LocalDate fabricationDate;
    private int airline;

    // empty constructor
    public Plane() {
    }

    // full constructor
    public Plane(String plate, int chairs, int status, int model, LocalDate fabricationDate, int airline) {
        this.plate = plate;
        this.chairs = chairs;
        this.status = status;
        this.model = model;
        this.fabricationDate = fabricationDate;
        this.airline = airline;
    }

    // getters and setters
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public LocalDate getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(LocalDate fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAirline() {
        return airline;
    }

    public void setAirline(int airline) {
        this.airline = airline;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }    
}
