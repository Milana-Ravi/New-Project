package com.inventory.inventoryservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.inventoryservice.Entity.Inventory;
import com.inventory.inventoryservice.ServiceImpl.InventoryServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl service;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Inventory> list = service.viewAllProducts();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody Inventory inventory) {
        try {
            return new ResponseEntity<>(service.addInventory(inventory), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/check/{productName}/{quantity}")
    public ResponseEntity<?> checkInventory(@PathVariable String productName, @PathVariable Long quantity) {
        try {
            boolean isAvailable = service.checkInventory(productName, quantity);
            return new ResponseEntity<>(isAvailable, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/available/{productName}")
    public ResponseEntity<?> getAvailableQuantity(@PathVariable String productName) {
        try {
            Long availableQuantity = service.getAvailableQuantity(productName);
            return new ResponseEntity<>(availableQuantity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deduct/{productName}/{quantity}")
    public ResponseEntity<?> deductInventory(@PathVariable String productName, @PathVariable Long quantity) {
        try {
            boolean isDeducted = service.deductInventory(productName, quantity);
            return new ResponseEntity<>(isDeducted, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add/{productName}/{quantity}")
    public ResponseEntity<?> addInventory(@PathVariable String productName, @PathVariable Long quantity) {
        try {
            boolean isAdded= service.addInventory(productName, quantity);
            return new ResponseEntity<>(isAdded, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
