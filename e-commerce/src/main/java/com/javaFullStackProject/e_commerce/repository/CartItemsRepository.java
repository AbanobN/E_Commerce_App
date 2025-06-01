package com.javaFullStackProject.e_commerce.repository;

import com.javaFullStackProject.e_commerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId , Long userId);
}
