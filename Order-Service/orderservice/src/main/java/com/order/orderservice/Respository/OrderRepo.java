package com.order.orderservice.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.orderservice.Entity.Orders;
@Repository
public interface OrderRepo extends JpaRepository<Orders,Long> {

}
