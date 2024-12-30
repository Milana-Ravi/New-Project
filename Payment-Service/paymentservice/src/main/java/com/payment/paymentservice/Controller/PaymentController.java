package com.payment.paymentservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.paymentservice.Entity.Payment;
import com.payment.paymentservice.ServiceImpl.PaymentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(path = "/api/payment")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @GetMapping("/getallpayments")
    public ResponseEntity<?> getAllPayments() {
        try {
            List<Payment> list = paymentService.getAllPayment();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/process/{orderId}/{amount}")
    public ResponseEntity<?> processPayment(@PathVariable Long orderId, @PathVariable Double amount) {
        try {
            boolean payment = paymentService.processPayment(orderId, amount);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing payment: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
