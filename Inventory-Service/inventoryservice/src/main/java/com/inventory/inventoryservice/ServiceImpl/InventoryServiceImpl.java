package com.inventory.inventoryservice.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.inventoryservice.Entity.Inventory;
import com.inventory.inventoryservice.Repository.InventoryRepo;
import com.inventory.inventoryservice.Service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepo repository;

    @Override
    public List<Inventory> viewAllProducts() {
        return repository.findAll();
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        Inventory existingInventory = repository.findByProductName(inventory.getProductName());
        if (existingInventory != null) {
            existingInventory.setQuantity(existingInventory.getQuantity() + inventory.getQuantity());
            return repository.save(existingInventory);
        } else {
            return repository.save(inventory); // Save new inventory entry
        }
    }

    @Override
    public boolean checkInventory(String productName, Long quantity) {
        Inventory inventory = repository.findByProductName(productName);
        return inventory != null && inventory.getQuantity() >= quantity;
    }

    @Override
    public Long getAvailableQuantity(String productName) {
        Inventory inventory = repository.findByProductName(productName);
        return inventory != null ? inventory.getQuantity() : 0L;
    }

    @Override
    public boolean deductInventory(String productName, Long quantity) {
        Inventory inventory = repository.findByProductName(productName);
        if (inventory != null && inventory.getQuantity() >= quantity) {
            inventory.setQuantity(inventory.getQuantity() - quantity);
            repository.save(inventory);
            return true;
        }
        return false;
    }
    @Override
    public boolean addInventory(String productName, Long quantity) {
        Inventory inventory = repository.findByProductName(productName);
        if (inventory != null && inventory.getQuantity() >= quantity) {
            inventory.setQuantity(inventory.getQuantity() + quantity);
            repository.save(inventory);
            return true;
        }
        return false;
    }
}
