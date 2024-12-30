package com.order.orderservice.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// @FeignClient(name = "payment-service", url = "http://localhost:8083")
@FeignClient(name = "payment-service", url = "${payment-service.url}")
public interface PaymentClient {

    @PostMapping("/api/payment/process/{orderId}/{amount}")
    boolean processPayment(@PathVariable Long orderId, @PathVariable Double amount);
}
