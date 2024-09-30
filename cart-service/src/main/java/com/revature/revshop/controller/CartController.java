package com.revature.revshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.revshop.dto.CartItemDto;
import com.revature.revshop.dto.OrderRequestDto;
import com.revature.revshop.dto.OrderResponse;
import com.revature.revshop.model.Cart;
import com.revature.revshop.service.CartService;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get the cart for a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    
    
    //method for adding items in cart as well as to update the cart also
    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItemToCart(@PathVariable String userId, @RequestBody CartItemDto itemDto) {
        try {
            Cart updatedCart = cartService.addItemToCart(userId, itemDto);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    // Remove an item from the cart
    @DeleteMapping("/{userId}/{productId}/delete")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable String userId, @PathVariable Long productId) {
        Cart updatedCart = cartService.removeItemFromCart(userId, productId);
        return updatedCart != null ? new ResponseEntity<>(updatedCart, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Clear the cart
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Place an order from the cart
    @PostMapping("/{userId}/placeOrder")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable String userId, @RequestBody OrderRequestDto orderRequest) {
        try {
            OrderResponse orderResponse = cartService.placeOrder(userId, orderRequest);
            return ResponseEntity.ok(orderResponse);
        } catch (RuntimeException e) {
            // Handle errors appropriately
            System.err.println("Error placing order: " + e.getMessage());
            return ResponseEntity.badRequest().body(null); // Or create a specific error response DTO
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(500).body(null); // Or create a specific error response DTO
        }
    }
}
