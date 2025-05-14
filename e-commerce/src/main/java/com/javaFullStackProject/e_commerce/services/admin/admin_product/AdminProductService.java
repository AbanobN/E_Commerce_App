package com.javaFullStackProject.e_commerce.services.admin.admin_product;

import com.javaFullStackProject.e_commerce.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {
    public ProductDto createProduct(ProductDto productDto) throws IOException;
    boolean deleteProduct(Long id);
    public List<ProductDto> getAllProducts();
    public List<ProductDto> getAllProductsByName(String Title);
}
