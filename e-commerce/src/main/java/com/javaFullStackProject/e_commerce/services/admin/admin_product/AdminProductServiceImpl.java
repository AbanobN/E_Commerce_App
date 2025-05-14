package com.javaFullStackProject.e_commerce.services.admin.admin_product;

import com.javaFullStackProject.e_commerce.dto.CategoryDto;
import com.javaFullStackProject.e_commerce.dto.ProductDto;
import com.javaFullStackProject.e_commerce.entity.Category;
import com.javaFullStackProject.e_commerce.entity.Product;
import com.javaFullStackProject.e_commerce.repository.CategoryRepository;
import com.javaFullStackProject.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) throws IOException {

        Optional<Product> existingProduct = productRepository.findByName(productDto.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalStateException("Product with name '" + productDto.getName() + "' already exists");
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg());

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " + productDto.getCategoryId() + " not found"));
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        ProductDto productResult = new ProductDto();

        productResult.setId(savedProduct.getId());
        productResult.setName(savedProduct.getName());
        productResult.setDescription(savedProduct.getDescription());
        productResult.setPrice(savedProduct.getPrice());
        productResult.setImg(savedProduct.getImg());
        productResult.setCategoryId(savedProduct.getCategory().getId());
        productResult.setCategoryName(savedProduct.getCategory().getName());
        return productResult;
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product>  optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductDto dto = new ProductDto();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setDescription(product.getDescription());
                    dto.setPrice(product.getPrice());
                    dto.setImg(product.getImg());
                    dto.setCategoryId(product.getCategory().getId());
                    dto.setCategoryName(product.getCategory().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProductsByName(String Title) {
        List<Product> products = productRepository.findAllByNameContaining(Title);
        return products.stream()
                .map(product -> {
                    ProductDto dto = new ProductDto();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setDescription(product.getDescription());
                    dto.setPrice(product.getPrice());
                    dto.setImg(product.getImg());
                    dto.setCategoryId(product.getCategory().getId());
                    dto.setCategoryName(product.getCategory().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
