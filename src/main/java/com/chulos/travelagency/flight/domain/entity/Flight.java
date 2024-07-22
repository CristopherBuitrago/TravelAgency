package com.chulos.travelagency.flight.domain.entity;

import java.time.LocalTime;

public class Flight {
    // attributes
    private int id;
    private int connectionNumber;
    private int tripId;
    private int planeId;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int availableChairs;
    
    // full constructor
    public Flight(int id, int connectionNumber, int tripId, int planeId, LocalTime departureTime, LocalTime arrivalTime,
            int availableChairs) {
        this.id = id;
        this.connectionNumber = connectionNumber;
        this.tripId = tripId;
        this.planeId = planeId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableChairs = availableChairs;
    }

    // getters and setters
    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConnectionNumber() {
        return connectionNumber;
    }

    public void setConnectionNumber(int connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableChairs() {
        return availableChairs;
    }

    public void setAvailableChairs(int availableChairs) {
        this.availableChairs = availableChairs;
    }

    
}