package com.order.orderservice.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// @FeignClient(name = "inventory-service", url = "http://localhost:8082")
@FeignClient(name = "inventory-service", url = "${inventory-service.url}")
public interface InventoryClient {

    @GetMapping("/api/inventory/check/{productName}/{quantity}")
    boolean checkInventory(@PathVariable("productName") String productName, @PathVariable("quantity") Long quantity);

    @GetMapping("/api/inventory/available/{productName}")
    Long getAvailableQuantity(@PathVariable("productName") String productName);

    @PostMapping("/api/inventory/deduct/{productName}/{quantity}")
    boolean deductInventory(@PathVariable("productName") String productName, @PathVariable("quantity") Long quantity);

    @PostMapping("/api/inventory/add/{productName}/{quantity}")
    boolean addInventory(@PathVariable("productName") String productName, @PathVariable("quantity") Long quantity);
}
