package com.inventory.inventoryservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.inventoryservice.Entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    // Inventory findByProductNameIgnoreCase(String productName);

    Inventory findByProductName(String productName);

}
