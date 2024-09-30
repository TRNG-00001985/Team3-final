package com.revature.revshop;

import com.revature.revshop.dto.CategoryDto;
import com.revature.revshop.dto.CategoryRequest;
import com.revature.revshop.model.Category;
import com.revature.revshop.repository.CategoryRepository;
import com.revature.revshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceApplicationTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryRequest categoryRequest;
    private Category category;

    @BeforeEach
    void setUp() {
        categoryRequest = CategoryRequest.builder()
                .name("Electronics")
                .logo("electronics.png")
                .build();

        category = Category.builder()
                .id(1L)
                .name("Electronics")
                .logo("electronics.png")
                .build();
    }

    @Test
    void createCategory_ShouldReturnCategoryDto() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto categoryDto = categoryService.createCategory(categoryRequest);

        assertNotNull(categoryDto);
        assertEquals("Electronics", categoryDto.getName());
        assertEquals("electronics.png", categoryDto.getLogo());
    }

    @Test
    void getCategoryById_ShouldReturnCategoryDto() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryDto categoryDto = categoryService.getCategoryById(1L);

        assertNotNull(categoryDto);
        assertEquals("Electronics", categoryDto.getName());
        assertEquals("electronics.png", categoryDto.getLogo());
    }

    @Test
    void updateCategoryById_ShouldReturnUpdatedCategoryDto() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto updatedCategoryDto = categoryService.updateCategoryById(1L, categoryRequest);

        assertNotNull(updatedCategoryDto);
        assertEquals("Electronics", updatedCategoryDto.getName());
        assertEquals("electronics.png", updatedCategoryDto.getLogo());
    }

    @Test
    void deleteCategoryById_ShouldReturnTrue() {
        doNothing().when(categoryRepository).deleteById(1L);

        boolean result = categoryService.deleteCategoryById(1L);

        assertTrue(result);
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategoryDtos() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));

        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        assertNotNull(categoryDtos);
        assertEquals(1, categoryDtos.size());
        assertEquals("Electronics", categoryDtos.get(0).getName());
    }
    @Test
    void contextLoads() {
        // This test ensures that the Spring context loads correctly
    }
}
