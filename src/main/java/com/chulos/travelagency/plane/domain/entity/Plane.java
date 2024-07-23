package com.chulos.travelagency.plane.domain.entity;

import java.sql.Date;

public class Plane {
    private String plate;
    private int chairs;
    private Date fabricationDate;
    private int status;
    private int airline;
    private int model;

    // empty constructor
    public Plane() {
    }

    // full constructor
    public Plane(String plate, int chairs, Date fabricationDate, int status, int airline, int model) {
        this.plate = plate;
        this.chairs = chairs;
        this.fabricationDate = fabricationDate;
        this.status = status;
        this.airline = airline;
        this.model = model;
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

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
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
