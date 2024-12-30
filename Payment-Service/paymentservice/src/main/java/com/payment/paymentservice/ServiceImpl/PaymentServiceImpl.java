package com.payment.paymentservice.ServiceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.paymentservice.Entity.Payment;
import com.payment.paymentservice.Repository.PaymentRepo;
import com.payment.paymentservice.Service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepo myRepo;

    @Override
    public List<Payment> getAllPayment() {
        return myRepo.findAll();
    }

    @Override
    public boolean processPayment(Long orderId, Double amount) {
        try {
            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setPaymentDate(LocalDate.now());
            payment.setAmount(amount);
            if(amount > 10){
                payment.setPaymentStatus("SUCCESS");
                myRepo.save(payment);
                return true;
            }
             payment.setPaymentStatus("FAILED");
             myRepo.save(payment);
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId) {
        return myRepo.findById(orderId).orElse(null);
    }

}
