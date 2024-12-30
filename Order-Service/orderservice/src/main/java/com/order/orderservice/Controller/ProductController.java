package com.order.orderservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.orderservice.Entity.Product;
import com.order.orderservice.ServiceImpl.ProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/viewproducts")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> list = productService.viewAllProducts();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addproduct")
    public ResponseEntity<?> addNewProduct(@RequestBody Product productObj) {
        try {
            Product newProduct = productService.addNewProduct(productObj);
            return new ResponseEntity<>(newProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
