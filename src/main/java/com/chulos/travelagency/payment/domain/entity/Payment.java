package com.chulos.travelagency.payment.domain.entity;

import java.util.Date;

public class Payment {
    // attributes
    private int id;
    private String paymentMethod;
    private String cardNumber;
    private double paymentAmount;
    private double paymentChange;
    private Date paymentDate;
    private int customerId;
    private int purchasedTrip;
    
    // full constructor
    public Payment(int id, String paymentMethod, String cardNumber, double paymentAmount, double paymentChange,
            Date paymentDate, int customerId, int purchasedTrip) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.paymentAmount = paymentAmount;
        this.paymentChange = paymentChange;
        this.paymentDate = paymentDate;
        this.customerId = customerId;
        this.purchasedTrip = purchasedTrip;
    }

    // empty constructor
    public Payment() {
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPaymentChange() {
        return paymentChange;
    }

    public void setPaymentChange(double paymentChange) {
        this.paymentChange = paymentChange;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPurchasedTrip() {
        return purchasedTrip;
    }

    public void setPurchasedTrip(int purchasedTrip) {
        this.purchasedTrip = purchasedTrip;
    }

}