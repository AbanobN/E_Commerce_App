package com.javaFullStackProject.e_commerce.services.customer;

import com.javaFullStackProject.e_commerce.dto.ProductDetailsDto;
import com.javaFullStackProject.e_commerce.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
    List<ProductDto> getAllProducts();
    List<ProductDto> getAllProductsByName(String Title);
    ProductDetailsDto getProductDetailsById(Long productId);
}
