package com.javaFullStackProject.e_commerce.services.admin.admin_order;

import com.javaFullStackProject.e_commerce.dto.OrderDto;
import com.javaFullStackProject.e_commerce.entity.Order;
import com.javaFullStackProject.e_commerce.enums.OrderStatus;
import com.javaFullStackProject.e_commerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminOrderServiceImp implements AdminOrderService{

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllPlacedOrders(){
        List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed , OrderStatus.Shipped , OrderStatus.Delivered));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());


    }
}
