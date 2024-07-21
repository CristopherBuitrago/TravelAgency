package com.chulos.travelagency.payment.domain.service;

import com.chulos.travelagency.payment.domain.entity.Payment;

public interface PaymentService {
    String createEfectPayment (Payment payment);
    String createTcPayment (Payment payment);
    String createTdPayment (Payment payment);
}
