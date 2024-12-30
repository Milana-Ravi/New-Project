package com.order.orderservice.Service;

import java.util.List;

import com.order.orderservice.Entity.Product;

public interface ProductService {


    public List<Product> viewAllProducts();

    public Product addNewProduct(Product productObj);
}
