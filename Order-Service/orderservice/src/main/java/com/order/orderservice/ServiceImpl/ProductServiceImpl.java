package com.order.orderservice.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.orderservice.Entity.Product;
import com.order.orderservice.Respository.ProductRepo;
import com.order.orderservice.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo myRepo;

    @Override
    public List<Product> viewAllProducts() {
        return myRepo.findAll();
    }

    @Override
    public Product addNewProduct(Product productObj) {
        return myRepo.save(productObj);
    }

}
