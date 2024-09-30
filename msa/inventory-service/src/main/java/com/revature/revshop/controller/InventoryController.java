package com.revature.revshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revshop.dto.InventoryRequest;
import com.revature.revshop.dto.InventoryResponse;
import com.revature.revshop.dto.OrderLineItemsDto;
import com.revature.revshop.service.InventoryService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	
	private final InventoryService inventoryService;
	
	@Autowired
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
		
	}
	
	
	@PostMapping
	public ResponseEntity<InventoryResponse> createInventoryEntry(@RequestBody InventoryRequest inventoryRequest) {
		
		return new ResponseEntity<>(inventoryService.createInventoryEntry(inventoryRequest), HttpStatus.CREATED);
		
	}
	
	@PostMapping("instock/")
	public ResponseEntity<List<Boolean>> areItemsInStock(@RequestBody List<OrderLineItemsDto> orderLineItems){
		
		return new ResponseEntity<>(inventoryService.areItemsInStock(orderLineItems), HttpStatus.OK);
		
	}	
	
	@PutMapping("/update")
    public ResponseEntity<InventoryResponse> updateInventory(@RequestParam Long id, @RequestBody InventoryRequest inventoryRequest) {
        InventoryResponse response = inventoryService.updateInventory(id, inventoryRequest);
        return ResponseEntity.ok(response);
    }

    // Delete an inventory entry
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        String message = inventoryService.deleteInventory(id);
        return ResponseEntity.ok(message);
    }

    // Get an inventory entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id) {
        InventoryResponse response = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(response);
    }

    // Get all inventory entries
    @GetMapping("/all")
    public ResponseEntity<List<InventoryResponse>> getAllInventoryEntries() {
        List<InventoryResponse> responses = inventoryService.getAllInventoryEntries();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/user")
    public ResponseEntity<InventoryResponse> getInventoryByUserId(String userId) {
        InventoryResponse inventoryResponse = inventoryService.getInventoryByUserId(userId);
        return ResponseEntity.ok(inventoryResponse);
    }

}
