package com.order.orderservice.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.orderservice.Entity.Orders;
import com.order.orderservice.Respository.OrderRepo;
import com.order.orderservice.Service.OrderService;

@Service
public class OrdersServiceImpl implements OrderService {

    @Autowired
    OrderRepo myRepo;

    @Override
    public List<Orders> viewAllOrders() {
        return myRepo.findAll();
    }

    @Override
    public Orders getOrderById(Long id) {
        return myRepo.findById(id).orElse(null);
    }

}
