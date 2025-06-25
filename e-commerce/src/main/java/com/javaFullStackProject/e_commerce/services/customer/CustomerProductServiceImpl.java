package com.javaFullStackProject.e_commerce.services.customer;

import com.javaFullStackProject.e_commerce.dto.ProductDetailsDto;
import com.javaFullStackProject.e_commerce.dto.ProductDto;
import com.javaFullStackProject.e_commerce.entity.FAQ;
import com.javaFullStackProject.e_commerce.entity.Product;
import com.javaFullStackProject.e_commerce.entity.Review;
import com.javaFullStackProject.e_commerce.repository.FAQRepository;
import com.javaFullStackProject.e_commerce.repository.ProductRepository;
import com.javaFullStackProject.e_commerce.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// Enhancement in video number : 37

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

    private final ProductRepository productRepository;

    private final FAQRepository faqRepository;

    private final ReviewRepository reviewRepository;

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

    @Override
    public ProductDetailsDto getProductDetailsById(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewList = reviewRepository.findAllByProductId(productId);

            ProductDetailsDto productDetailsDto = new ProductDetailsDto();

            productDetailsDto.setProductDto(optionalProduct.get().getProductDto());
            productDetailsDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            productDetailsDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));

            return productDetailsDto;
        }

        return null;
    }
}
