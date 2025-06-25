package com.javaFullStackProject.e_commerce.services.customer.review;

import com.javaFullStackProject.e_commerce.dto.OrderedProductsResponseDto;
import com.javaFullStackProject.e_commerce.dto.ProductDto;
import com.javaFullStackProject.e_commerce.dto.ReviewDto;
import com.javaFullStackProject.e_commerce.entity.*;
import com.javaFullStackProject.e_commerce.repository.OrderRepository;
import com.javaFullStackProject.e_commerce.repository.ProductRepository;
import com.javaFullStackProject.e_commerce.repository.ReviewRepository;
import com.javaFullStackProject.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();

        if(optionalOrder.isPresent()){
            orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            List<ProductDto> productDtoList = new ArrayList<>();
            for(CartItem cartItems: optionalOrder.get().getCartItems()){
                ProductDto productDto = new ProductDto();

                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getProduct().getPrice());
                productDto.setImg(cartItems.getProduct().getImg());
                productDto.setQuantity(cartItems.getQuantity());

                productDtoList.add(productDto);
            }

            orderedProductsResponseDto.setProductDtoList(productDtoList);
        }

        return orderedProductsResponseDto;
    }


    @Override
    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProduct_id());
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUser_id());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review = new Review();
            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getRetunedImg().getBytes());

            return reviewRepository.save(review).getDto();
        }

        return null;
    }
}
