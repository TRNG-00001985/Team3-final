package com.revature.revshop.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.revshop.dto.ReviewRequest;
import com.revature.revshop.dto.ReviewResponse;
import com.revature.revshop.model.Product;
import com.revature.revshop.model.Review;
import com.revature.revshop.repository.ProductRepository;
import com.revature.revshop.repository.ReviewRepository;

@Service
public class ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    public Review mapToReview(ReviewRequest reviewRequest) {
        logger.info("Mapping ReviewRequest to Review for Product ID: {}", reviewRequest.getProductId());

        Product product = productRepository.findById(reviewRequest.getProductId()).orElseThrow(() -> {
            logger.error("Product not found with ID: {}", reviewRequest.getProductId());
            return new RuntimeException("Product not found");
        });

        logger.debug("Product found: {}", product.getName());

        return Review.builder()
                .product(product)
                .userId(reviewRequest.getUserId())
                .rating(reviewRequest.getRating())
                .reviewText(reviewRequest.getReviewText())
                .build();
    }

    public ReviewResponse mapToReviewResponse(Review review) {
        logger.info("Mapping Review to ReviewResponse for Review ID: {}", review.getId());

        return ReviewResponse.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .userId(review.getUserId())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .build();
    }

    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        logger.info("Creating review for Product ID: {}", reviewRequest.getProductId());

        Review review = mapToReview(reviewRequest);
        review = reviewRepository.save(review);

        logger.info("Review created with ID: {}", review.getId());
        return mapToReviewResponse(review);
    }

    public ReviewResponse getReviewById(Long id) {
        logger.info("Retrieving review with ID: {}", id);

        Review review = reviewRepository.findById(id).orElseThrow(() -> {
            logger.error("Review not found with ID: {}", id);
            return new RuntimeException("Review not found");
        });

        logger.info("Review retrieved for Product: {}", review.getProduct().getName());
        return mapToReviewResponse(review);
    }

    public ReviewResponse updateReviewById(ReviewRequest reviewRequest, Long id) {
        logger.info("Updating review with ID: {}", id);

        Review review = mapToReview(reviewRequest);
        review.setId(id);
        review = reviewRepository.save(review);

        logger.info("Review updated with ID: {}", review.getId());
        return mapToReviewResponse(review);
    }

    public boolean deleteReviewById(Long id) {
        logger.info("Deleting review with ID: {}", id);
        try {
            reviewRepository.deleteById(id);
            logger.info("Review deleted successfully with ID: {}", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting review with ID: {}", id, e);
            return false;
        }
    }

    public List<ReviewResponse> getAllReviewsByProductId(Long productId) {
        logger.info("Retrieving all reviews for Product ID: {}", productId);

        List<Review> reviews = reviewRepository.findByProductId(productId);

        logger.info("Total reviews found: {}", reviews.size());
        return reviews.stream().map(this::mapToReviewResponse).toList();
    }

    public List<ReviewResponse> getAllReviewsByUserId(String userId) {
        logger.info("Retrieving all reviews for User ID: {}", userId);

        List<Review> reviews = reviewRepository.findByUserId(userId);

        logger.info("Total reviews found: {}", reviews.size());
        return reviews.stream().map(this::mapToReviewResponse).toList();
    }
}
