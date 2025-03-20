package com.javaFullStackProject.e_commerce.services.admin.category;

import com.javaFullStackProject.e_commerce.dto.CategoryDto;
import com.javaFullStackProject.e_commerce.entity.Category;

public interface CategoryService {
    public Category createCategory(CategoryDto categoryDto);
}
