package com.payment.paymentservice.Service;

import java.util.List;

import com.payment.paymentservice.Entity.Payment;

public interface PaymentService {

    public List<Payment> getAllPayment();

    public boolean processPayment(Long orderId, Double amount);

    public Payment getPaymentByOrderId(Long orderId);
}
