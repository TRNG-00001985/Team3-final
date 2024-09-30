package com.revature.revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.revshop.dto.ProductResponse;
import com.revature.revshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<ProductResponse> findByNameContaining(String name);
    
    List<Product> findByNameContainingIgnoreCase(String name); // Update this method

    boolean existsBySkuCode(String skuCode);
    List<Product> findByUserId(String userId); 

}
