package com.chulos.travelagency.trip.domain.entity;

import java.util.Date;

public class Trip {
    // attributes
    private int id;
    private Date date;
    private double price;
    private int flightFareId;
    private String flightFareTitle;

    // empty constructor
    public Trip() {
    }

    // constructor without flightFareTitle
    public Trip(int id, Date date, double price, int flightFareId) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.flightFareId = flightFareId;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFlightFareId() {
        return flightFareId;
    }

    public void setFlightFareId(int flightFareId) {
        this.flightFareId = flightFareId;
    }

    public String getFlightFareTitle() {
        return flightFareTitle;
    }

    public void setFlightFareTitle(String flightFareTitle) {
        this.flightFareTitle = flightFareTitle;
    }

    
}
