package com.revature.revshop.service;

import com.revature.revshop.dto.CategoryDto;
import com.revature.revshop.dto.CategoryRequest;
import com.revature.revshop.model.Category;
import com.revature.revshop.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Map CategoryRequest to Category Entity
    public Category mapToCategory(CategoryRequest categoryRequest) {
        logger.info("Mapping CategoryRequest to Category for name: {}", categoryRequest.getName());
        return Category.builder()
                .name(categoryRequest.getName())
                .logo(categoryRequest.getLogo())
                .build();
    }

    // Map Category Entity to CategoryDto
    public CategoryDto mapToCategoryDto(Category category) {
        logger.info("Mapping Category to CategoryDto for category ID: {}", category.getId());
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .logo(category.getLogo())
                .build();
    }

    // Create Category
    public CategoryDto createCategory(CategoryRequest categoryRequest) {
        logger.info("Creating new category with name: {}", categoryRequest.getName());
        Category category = mapToCategory(categoryRequest);
        category = categoryRepository.save(category);
        logger.info("Category created with ID: {}", category.getId());
        return mapToCategoryDto(category);
    }

    // Get Category by Id
    public CategoryDto getCategoryById(Long id) {
        logger.info("Fetching category with ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Category not found with ID: {}", id);
                    return new RuntimeException("Category not found");
                });
        logger.info("Category found: {}", category.getName());
        return mapToCategoryDto(category);
    }

    // Update Category by Id
    public CategoryDto updateCategoryById(Long id, CategoryRequest categoryRequest) {
        logger.info("Updating category with ID: {}", id);
        Category category = mapToCategory(categoryRequest);
        category.setId(id);
        category = categoryRepository.save(category);
        logger.info("Category updated with ID: {}", category.getId());
        return mapToCategoryDto(category);
    }

    // Delete Category by Id
    public boolean deleteCategoryById(Long id) {
        logger.info("Deleting category with ID: {}", id);
        try {
            categoryRepository.deleteById(id);
            logger.info("Category deleted successfully with ID: {}", id);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while deleting category with ID: {}", id, e);
            return false;
        }
    }

    // Get All Categories
    public List<CategoryDto> getAllCategories() {
        logger.info("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        logger.info("Total categories found: {}", categories.size());
        return categories.stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toList());
    }
}
