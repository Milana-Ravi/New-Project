package com.order.orderservice.Service;

import java.util.List;

import com.order.orderservice.Entity.Orders;

public interface OrderService {

    public List<Orders> viewAllOrders();
    Orders getOrderById(Long id);
}
