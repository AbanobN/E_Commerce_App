package com.javaFullStackProject.e_commerce.services.customer.review;

import com.javaFullStackProject.e_commerce.dto.OrderedProductsResponseDto;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
}
