package com.inventory.inventoryservice.Service;

import java.util.List;
import com.inventory.inventoryservice.Entity.Inventory;

public interface InventoryService {
    List<com.inventory.inventoryservice.Entity.Inventory> viewAllProducts();
    Inventory addInventory(Inventory inventory);
    boolean checkInventory(String productName, Long quantity);
    Long getAvailableQuantity(String productName);
    boolean deductInventory(String productName, Long quantity);
    boolean addInventory(String productName, Long quantity);
    
}
