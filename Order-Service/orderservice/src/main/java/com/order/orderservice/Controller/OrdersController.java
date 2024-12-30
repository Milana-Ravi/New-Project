package com.order.orderservice.Controller;


import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.order.orderservice.Entity.Orders;
import com.order.orderservice.Entity.Product;
import com.order.orderservice.Respository.OrderRepo;
import com.order.orderservice.Respository.ProductRepo;
import com.order.orderservice.ServiceImpl.OrdersServiceImpl;

@RestController
@RequestMapping(path = "/api/orders")
public class OrdersController {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    InventoryClient inventoryClient;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrdersServiceImpl orderService;

    @GetMapping("/vieworders")
    public ResponseEntity<?> viewAllOrders() {
        try {
            List<Orders> ordersList = orderService.viewAllOrders();
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vieworder/{id}")
    public ResponseEntity<?> viewOrderById(@PathVariable Long id) {
        try {
            Orders order = orderService.getOrderById(id);
            if (order == null) {
                return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/placeorder/{productName}/{quantity}")
    public ResponseEntity<?> placeOrder(@PathVariable String productName, @PathVariable Long quantity) {
        try {
            // Check if the product exists in the inventory
            Product product = productRepo.findByProductName(productName);
            if (product == null) {
                return new ResponseEntity<>("Product not found in the Inventory.", HttpStatus.NOT_FOUND);
            } // Check inventory availability
            boolean isAvailable = inventoryClient.checkInventory(productName, quantity);
            if (!isAvailable) {
                Long availableQuantity = inventoryClient.getAvailableQuantity(productName);
                if (availableQuantity < quantity) {
                    return new ResponseEntity<>(
                            "Requested quantity exceeds available inventory. Available quantity: " + availableQuantity,
                            HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>("Product is out of stock.", HttpStatus.NOT_FOUND);
                }
            }
            // Calculate the total amount
            Double amount = product.getPrice() * quantity;
            // Deduct inventory
            boolean isDeducted = inventoryClient.deductInventory(productName, quantity);
            if (!isDeducted) {
                return new ResponseEntity<>("Failed to deduct inventory.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // Create and save order
            Orders order = new Orders();
            order.setOrderDate(LocalDate.now());
            order.setPaymentStatus("PENDING");
            order.setProductQuantity(quantity);
            order.setProducts(product);
            orderRepo.save(order);
            // Process payment
            boolean paymentResponse = paymentClient.processPayment(order.getOrderId(), amount);
            if (!paymentResponse) {
                order.setPaymentStatus("FAILED");
                inventoryClient.addInventory(productName, quantity);
                orderRepo.save(order);
                return new ResponseEntity<>("Process payment failed.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            order.setPaymentStatus("COMPLETED");
            orderRepo.save(order);
            return new ResponseEntity<>("Order is placed successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error placing order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
