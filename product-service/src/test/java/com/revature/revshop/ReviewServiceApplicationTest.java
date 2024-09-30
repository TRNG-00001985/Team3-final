package com.revature.revshop;

import com.revature.revshop.dto.ReviewRequest;
import com.revature.revshop.dto.ReviewResponse;
import com.revature.revshop.model.Product;
import com.revature.revshop.model.Review;
import com.revature.revshop.repository.ProductRepository;
import com.revature.revshop.repository.ReviewRepository;
import com.revature.revshop.service.ReviewService;

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
class ReviewServiceApplicationTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ReviewService reviewService;

    private ReviewRequest reviewRequest;
    private Review review;
    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("Laptop")
                .build();

        reviewRequest = ReviewRequest.builder()
                .productId(1L)
                .userId("user123")
                .rating(5)
                .reviewText("Great product!")
                .build();

        review = Review.builder()
                .id(1L)
                .product(product)
                .userId("user123")
                .rating(5)
                .reviewText("Great product!")
                .build();
    }

    @Test
    void createReview_ShouldReturnReviewResponse() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewResponse response = reviewService.createReview(reviewRequest);

        assertNotNull(response);
        assertEquals("user123", response.getUserId());
        assertEquals(5, response.getRating());
        assertEquals("Great product!", response.getReviewText());
    }

    @Test
    void getReviewById_ShouldReturnReviewResponse() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewResponse response = reviewService.getReviewById(1L);

        assertNotNull(response);
        assertEquals("user123", response.getUserId());
        assertEquals(5, response.getRating());
    }

    @Test
    void updateReviewById_ShouldReturnUpdatedReviewResponse() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewResponse response = reviewService.updateReviewById(reviewRequest, 1L);

        assertNotNull(response);
        assertEquals("user123", response.getUserId());
        assertEquals(5, response.getRating());
    }

    @Test
    void deleteReviewById_ShouldReturnTrue() {
        doNothing().when(reviewRepository).deleteById(1L);

        boolean result = reviewService.deleteReviewById(1L);

        assertTrue(result);
    }

    @Test
    void getAllReviewsByProductId_ShouldReturnListOfReviewResponses() {
        when(reviewRepository.findByProductId(1L)).thenReturn(Arrays.asList(review));

        List<ReviewResponse> responses = reviewService.getAllReviewsByProductId(1L);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("user123", responses.get(0).getUserId());
    }

    @Test
    void getAllReviewsByUserId_ShouldReturnListOfReviewResponses() {
        when(reviewRepository.findByUserId("user123")).thenReturn(Arrays.asList(review));

        List<ReviewResponse> responses = reviewService.getAllReviewsByUserId("user123");

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("user123", responses.get(0).getUserId());
    }

    @Test
    void contextLoads() {
        // This test ensures that the Spring context loads correctly
    }
}
