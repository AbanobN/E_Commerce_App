package com.javaFullStackProject.e_commerce.controller.customer;

import com.javaFullStackProject.e_commerce.dto.AddProductInCartDto;
import com.javaFullStackProject.e_commerce.dto.OrderDto;
import com.javaFullStackProject.e_commerce.exceptions.ValidationException;
import com.javaFullStackProject.e_commerce.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto){
        return cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/coupon/{userId}/{couponCode}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String couponCode){
        try {
            OrderDto orderDto = cartService.applyCoupon(userId, couponCode);
            return ResponseEntity.ok(orderDto);
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
