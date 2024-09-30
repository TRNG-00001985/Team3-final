package com.revature.revshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revshop.controller.ReviewController;
import com.revature.revshop.dto.ReviewRequest;
import com.revature.revshop.dto.ReviewResponse;
import com.revature.revshop.service.ReviewService;
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

@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private ReviewResponse reviewResponse;
    private ReviewRequest reviewRequest;

    @BeforeEach
    public void setup() {
        reviewResponse = ReviewResponse.builder()
                .id(1L)
                .productId(1L)
                .userId("user123")
                .rating(5)
                .reviewText("Great product!")
                .build();

        reviewRequest = ReviewRequest.builder()
                .productId(1L)
                .userId("user123")
                .rating(5)
                .reviewText("Great product!")
                .build();
    }

    @Test
    public void testCreateReview() throws Exception {
        Mockito.when(reviewService.createReview(any(ReviewRequest.class))).thenReturn(reviewResponse);

        mockMvc.perform(post("/api/review/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(reviewResponse.getId()))
                .andExpect(jsonPath("$.userId").value(reviewResponse.getUserId()));
    }

    @Test
    public void testGetReviewById() throws Exception {
        Mockito.when(reviewService.getReviewById(1L)).thenReturn(reviewResponse);

        mockMvc.perform(get("/api/review/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reviewResponse.getId()))
                .andExpect(jsonPath("$.userId").value(reviewResponse.getUserId()));
    }

    @Test
    public void testUpdateReviewById() throws Exception {
        Mockito.when(reviewService.updateReviewById(any(ReviewRequest.class), eq(1L))).thenReturn(reviewResponse);

        mockMvc.perform(put("/api/review/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewRequest)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(reviewResponse.getId()))
                .andExpect(jsonPath("$.rating").value(reviewResponse.getRating()));
    }

    @Test
    public void testDeleteReviewById() throws Exception {
        Mockito.when(reviewService.deleteReviewById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/review/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetAllReviewsByProductId() throws Exception {
        List<ReviewResponse> reviews = Arrays.asList(reviewResponse);
        Mockito.when(reviewService.getAllReviewsByProductId(1L)).thenReturn(reviews);

        mockMvc.perform(get("/api/review/product/{productId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(reviewResponse.getId()))
                .andExpect(jsonPath("$[0].userId").value(reviewResponse.getUserId()));
    }

    @Test
    public void testGetAllReviewsByUserId() throws Exception {
        List<ReviewResponse> reviews = Arrays.asList(reviewResponse);
        Mockito.when(reviewService.getAllReviewsByUserId("user123")).thenReturn(reviews);

        mockMvc.perform(get("/api/review/user/{userId}", "user123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(reviewResponse.getId()))
                .andExpect(jsonPath("$[0].userId").value(reviewResponse.getUserId()));
    }
}
