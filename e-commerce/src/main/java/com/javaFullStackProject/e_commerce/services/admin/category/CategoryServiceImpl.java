package com.javaFullStackProject.e_commerce.services.admin.category;

import com.javaFullStackProject.e_commerce.dto.CategoryDto;
import com.javaFullStackProject.e_commerce.entity.Category;
import com.javaFullStackProject.e_commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // Check for duplicate category name
        Optional<Category> existingCategory = categoryRepository.findByName(categoryDto.getName());
        if (existingCategory.isPresent()) {
            throw new IllegalStateException("Category with name '" + categoryDto.getName() + "' already exists");
        }

        // Map DTO to entity
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        // Save and map back to DTO
        Category savedCategory = categoryRepository.save(category);
        CategoryDto resultDto = new CategoryDto();
        resultDto.setId(savedCategory.getId());
        resultDto.setName(savedCategory.getName());
        resultDto.setDescription(savedCategory.getDescription());

        return resultDto;
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> {
                    CategoryDto dto = new CategoryDto();
                    dto.setId(category.getId());
                    dto.setName(category.getName());
                    dto.setDescription(category.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}