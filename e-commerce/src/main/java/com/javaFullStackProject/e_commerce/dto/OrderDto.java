package com.javaFullStackProject.e_commerce.dto;

import com.javaFullStackProject.e_commerce.enums.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private Long id;

    private String orderDescription;

    private Date date;

    private String address;

    private String payment;

    private Long totalAmount;

    private Long discount;

    private Long amount;

    private OrderStatus orderStatus;

    private UUID trackingId;

    private String userName;

    private String couponName;

    private List<CartItemsDto> cartItems;
}
