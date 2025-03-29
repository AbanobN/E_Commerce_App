package com.javaFullStackProject.e_commerce.services.admin.category;

import com.javaFullStackProject.e_commerce.dto.CategoryDto;
import com.javaFullStackProject.e_commerce.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);
    List<CategoryDto> getAllCategories();
}
