package com.revature.revshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.revshop.dto.ReviewRequest;
import com.revature.revshop.dto.ReviewResponse;
import com.revature.revshop.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest) {
        return new ResponseEntity<>(reviewService.createReview(reviewRequest), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReviewById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReviewResponse> updateReviewById(@RequestBody ReviewRequest reviewRequest, @PathVariable Long id) {
        return new ResponseEntity<>(reviewService.updateReviewById(reviewRequest, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteReviewById(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.deleteReviewById(id), HttpStatus.OK);
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getAllReviewsByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(reviewService.getAllReviewsByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<ReviewResponse>> getAllReviewsByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(reviewService.getAllReviewsByUserId(userId), HttpStatus.OK);
    }
}
