package com.javaFullStackProject.e_commerce.services.customer.review;

import com.javaFullStackProject.e_commerce.dto.OrderedProductsResponseDto;
import com.javaFullStackProject.e_commerce.dto.ReviewDto;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
    ReviewDto giveReview(ReviewDto reviewDto);
}
