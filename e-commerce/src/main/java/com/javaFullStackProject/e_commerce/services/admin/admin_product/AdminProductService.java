package com.javaFullStackProject.e_commerce.services.admin.admin_product;

import com.javaFullStackProject.e_commerce.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {
    ProductDto createProduct(ProductDto productDto) throws IOException;
    boolean deleteProduct(Long id);
    List<ProductDto> getAllProducts();
    List<ProductDto> getAllProductsByName(String Title);
    ProductDto getProductById(Long productId);
    ProductDto updateProduct(Long productId, ProductDto productDto);

}
