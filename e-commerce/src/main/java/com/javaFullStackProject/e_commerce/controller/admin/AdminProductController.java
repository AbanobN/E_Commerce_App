package com.javaFullStackProject.e_commerce.controller.admin;

import com.javaFullStackProject.e_commerce.dto.CategoryDto;
import com.javaFullStackProject.e_commerce.dto.FAQDto;
import com.javaFullStackProject.e_commerce.dto.ProductDto;
import com.javaFullStackProject.e_commerce.services.admin.admin_product.AdminProductService;
import com.javaFullStackProject.e_commerce.services.admin.faq.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    private final FAQService faqService;

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto){
        ProductDto updatedProduct = adminProductService.updateProduct(productId , productDto);

        if(updatedProduct != null){
            return ResponseEntity.ok(updatedProduct);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        ProductDto productDto = adminProductService.getProductById(productId);

        if(productDto != null){
            return ResponseEntity.ok(productDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId, @RequestBody FAQDto faqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId,faqDto));
    }


    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Long price,
            @RequestParam("description") String description,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("img") MultipartFile img) throws IOException {

        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setDescription(description);
        productDto.setCategoryId(categoryId);
        productDto.setImg(img.getBytes());

        ProductDto createdProduct = adminProductService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = adminProductService.deleteProduct(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = adminProductService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String title) {
        List<ProductDto> products = adminProductService.getAllProductsByName(title);
        return ResponseEntity.ok(products);
    }
}
