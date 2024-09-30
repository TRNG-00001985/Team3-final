package com.revature.revshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revshop.dto.CategoryRequest;
import com.revature.revshop.controller.CategoryController;
import com.revature.revshop.dto.CategoryDto;
import com.revature.revshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryDto categoryDto;
    private CategoryRequest categoryRequest;

    @BeforeEach
    public void setup() {
        categoryDto = CategoryDto.builder()
                .id(1L)
                .name("Electronics")
                .logo("electronics-logo.png")
                .build();

        categoryRequest = CategoryRequest.builder()
                .name("Electronics")
                .logo("electronics-logo.png")
                .build();
    }

    @Test
    public void testCreateCategory() throws Exception {
        Mockito.when(categoryService.createCategory(any(CategoryRequest.class))).thenReturn(categoryDto);

        mockMvc.perform(post("/api/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(categoryDto.getId()))
                .andExpect(jsonPath("$.name").value(categoryDto.getName()));
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(categoryDto);

        mockMvc.perform(get("/api/category/")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryDto.getId()))
                .andExpect(jsonPath("$.name").value(categoryDto.getName()));
    }

    @Test
    public void testUpdateCategoryById() throws Exception {
        Mockito.when(categoryService.updateCategoryById(eq(1L), any(CategoryRequest.class))).thenReturn(categoryDto);

        mockMvc.perform(put("/api/category/")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(categoryDto.getId()))
                .andExpect(jsonPath("$.name").value(categoryDto.getName()));
    }

    @Test
    public void testDeleteCategoryById() throws Exception {
        Mockito.when(categoryService.deleteCategoryById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/category/")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<CategoryDto> categories = Arrays.asList(categoryDto);
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(categoryDto.getId()))
                .andExpect(jsonPath("$[0].name").value(categoryDto.getName()));
    }
}
