package com.revature.revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revshop.model.CartItem;

@Repository
public interface CartItemRepositroy extends JpaRepository<CartItem, Long>{
	

}
