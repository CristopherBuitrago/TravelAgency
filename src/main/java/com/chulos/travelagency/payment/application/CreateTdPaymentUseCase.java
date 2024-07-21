package com.chulos.travelagency.payment.application;

import com.chulos.travelagency.payment.domain.entity.Payment;
import com.chulos.travelagency.payment.domain.service.PaymentService;

public class CreateTdPaymentUseCase {
    // get service
    private final PaymentService paymentService;

    // constructor
    public CreateTdPaymentUseCase(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // execute method
    public String execute (Payment payment) {
        return paymentService.createTdPayment(payment);
    }
}