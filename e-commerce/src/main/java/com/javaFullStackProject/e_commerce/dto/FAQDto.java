package com.javaFullStackProject.e_commerce.dto;

import com.javaFullStackProject.e_commerce.entity.Product;
import lombok.Data;

@Data
public class FAQDto {

    private Long id;

    private String question;

    private String answer;

    private Long productId;
}
