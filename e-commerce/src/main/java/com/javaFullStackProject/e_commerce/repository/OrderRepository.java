package com.javaFullStackProject.e_commerce.repository;

import com.javaFullStackProject.e_commerce.entity.Order;
import com.javaFullStackProject.e_commerce.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
