package com.javaFullStackProject.e_commerce.services.customer.cart;

import com.javaFullStackProject.e_commerce.dto.AddProductInCartDto;
import com.javaFullStackProject.e_commerce.dto.OrderDto;
import com.javaFullStackProject.e_commerce.dto.PlacedOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId(Long userId);
    OrderDto applyCoupon(Long userId, String CouponCode);
    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto placedOrder(PlacedOrderDto placedOrderDto);
    List<OrderDto> getMyPlacedOrders(Long userId);
    OrderDto searchOrderByTrackingId(UUID trackingId);
}
