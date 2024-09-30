package com.revature.revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByUserId(String userId); // Get orders by user ID

    List<Order> findByStatus(String status); // Get orders by status
}
