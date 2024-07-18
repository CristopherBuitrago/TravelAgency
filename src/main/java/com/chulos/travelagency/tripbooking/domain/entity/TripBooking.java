package com.chulos.travelagency.tripbooking.domain.entity;

import java.util.Date;

public class TripBooking {
    // attributes
    private int id;
    private int customerId;
    private int tripId;
    private int paymentId;
    private int flightFareId;
    private Date reservationDate;
    private String customerName;
    private String flightFareTitle;

    // empty constructor
    public TripBooking() {
    }

    // constructor without customer name and flight fare title
    public TripBooking(int id, int customerId, int tripId, int paymentId, int flightFareId, Date reservationDate) {
        this.id = id;
        this.customerId = customerId;
        this.tripId = tripId;
        this.paymentId = paymentId;
        this.flightFareId = flightFareId;
        this.reservationDate = reservationDate;
    }

    // constructor without customer id and flight fare id
    public TripBooking(int id, int tripId, int paymentId, Date reservationDate, String customerName,
            String flightFareTitle) {
        this.id = id;
        this.tripId = tripId;
        this.paymentId = paymentId;
        this.reservationDate = reservationDate;
        this.customerName = customerName;
        this.flightFareTitle = flightFareTitle;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getFlightFareId() {
        return flightFareId;
    }

    public void setFlightFareId(int flightFareId) {
        this.flightFareId = flightFareId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFlightFareTitle() {
        return flightFareTitle;
    }

    public void setFlightFareTitle(String flightFareTitle) {
        this.flightFareTitle = flightFareTitle;
    }   
}