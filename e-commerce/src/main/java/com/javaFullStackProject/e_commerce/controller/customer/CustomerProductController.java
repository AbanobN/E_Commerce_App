package com.javaFullStackProject.e_commerce.controller.customer;

import com.javaFullStackProject.e_commerce.dto.ProductDetailsDto;
import com.javaFullStackProject.e_commerce.dto.ProductDto;
import com.javaFullStackProject.e_commerce.services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = customerProductService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String title) {
        List<ProductDto> products = customerProductService.getAllProductsByName(title);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailsDto> getProductDetailsById(@PathVariable Long productId){
        ProductDetailsDto productDetailsDto = customerProductService.getProductDetailsById(productId);

        if(productDetailsDto == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productDetailsDto);
    }
}
