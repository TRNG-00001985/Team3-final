package com.revature.revshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revshop.model.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	Inventory findInventoryBySkuCode(String skucode);
	
	// New method to find inventory by user ID
    Optional<Inventory> findByUserId(String userId);
	

}
