package com.javaFullStackProject.e_commerce.services.customer;

import com.javaFullStackProject.e_commerce.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    public List<ProductDto> getAllProducts();

    public List<ProductDto> getAllProductsByName(String Title);
}
