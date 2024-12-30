package com.payment.paymentservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.paymentservice.Entity.Payment;
@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {

}
