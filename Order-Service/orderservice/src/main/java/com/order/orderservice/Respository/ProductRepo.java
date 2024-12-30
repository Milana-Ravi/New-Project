package com.order.orderservice.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.orderservice.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // Product findByProductNameIgnoreCase(String productName);

    Product findByProductName(String productName);

}
