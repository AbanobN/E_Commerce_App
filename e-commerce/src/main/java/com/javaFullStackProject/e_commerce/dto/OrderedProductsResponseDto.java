package com.javaFullStackProject.e_commerce.dto;

import com.javaFullStackProject.e_commerce.entity.Product;
import com.javaFullStackProject.e_commerce.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class OrderedProductsResponseDto {

    private List<ProductDto> productDtoList;

    private Long orderAmount;

}
