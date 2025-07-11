package com.javaFullStackProject.e_commerce.entity;

import com.javaFullStackProject.e_commerce.dto.OrderDto;
import com.javaFullStackProject.e_commerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItem> cartItems;


    public OrderDto getOrderDto(){
        OrderDto orderDto = new OrderDto();

        orderDto.setId(id);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setDate(date);
        orderDto.setAddress(address);
//        orderDto.setPayment(payment);
//        orderDto.setTotalAmount(totalAmount);
//        orderDto.setDiscount(discount);
        orderDto.setAmount(amount);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setTrackingId(trackingId);
        orderDto.setUserName(user.getName());
        if(coupon != null){
            orderDto.setCouponName(coupon.getName());
        }

        return orderDto;
    }


}
