package com.chulos.travelagency.tripbooking.domain.entity;

import java.util.Date;

public class TripBooking {
    // attributes
    private int id;
    private int customerId;
    private int tripId;
    private int paymentId;
    private Date reservationDate;
    private String customer;
    private double payment;
    
    // constructor without customer and payment
    public TripBooking(int id, int customerId, int tripId, int paymentId, Date reservationDate) {
        this.id = id;
        this.customerId = customerId;
        this.tripId = tripId;
        this.paymentId = paymentId;
        this.reservationDate = reservationDate;
    }

    // constructor without customerId and paymentId
    public TripBooking(int id, String customer, int tripId, double payment, Date reservationDate) {
        this.id = id;
        this.tripId = tripId;
        this.reservationDate = reservationDate;
        this.customer = customer;
        this.payment = payment;
    }

    // empty constructor
    public TripBooking() {
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}