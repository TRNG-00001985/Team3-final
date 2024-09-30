package com.revature.revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.revshop.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // Additional custom queries can be added here if necessary, for example:
     List<Review> findByProductId(Long productId);
     List<Review> findByUserId(String userId);
}
