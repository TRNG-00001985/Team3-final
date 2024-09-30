package com.revature.revshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.revature.revshop.dto.CartItemDto;
import com.revature.revshop.dto.OrderLineItemsDto;
import com.revature.revshop.dto.OrderRequestDto;
import com.revature.revshop.dto.OrderResponse;
import com.revature.revshop.dto.OrderStatus;
import com.revature.revshop.dto.ProductDto;
import com.revature.revshop.model.Cart;
import com.revature.revshop.model.CartItem;
import com.revature.revshop.repository.CartRepository;

import reactor.core.publisher.Mono;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private WebClient webClient;

    private final String ORDER_SERVICE_URL = "http://localhost:8082/api/order";

    // Get the cart for a specific user
    public Cart getCartByUserId(String userId) {
        System.out.println("Fetching cart for user: " + userId);
        return cartRepository.findByUserId(userId);
    }

    public ProductDto getProductDetails(Long productId) {
        System.out.println("Fetching product details for product ID: " + productId);
        return webClient.get()
            .uri("/{id}", productId)  // Appends to the base URL set in WebClientConfig
            .retrieve()
            .bodyToMono(ProductDto.class)
            .block(); // Synchronously wait for the response
    }

    @Transactional
    public Cart addItemToCart(String userId, CartItemDto itemDto) {
        System.out.println("Adding item to cart for user: " + userId + ", item: " + itemDto);

        // Fetch product details to get the latest information
        ProductDto product = getProductDetails(itemDto.getProductId());
        if (product == null) {
            System.err.println("Product not found for ID: " + itemDto.getProductId());
            throw new RuntimeException("Product not found");
        }

        // Retrieve or create the cart
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = createCartForUser(userId);
        }

        // Create a CartItem from the DTO
        CartItem item = createCartItem(product, itemDto.getQuantity());
        item.setCart(cart); // Associate the CartItem with the Cart

        // Add or update the item in the cart
        updateOrAddCartItem(cart, item);

        System.out.println("Item added to cart successfully for user: " + userId);
        return cartRepository.save(cart);
    }

    private CartItem createCartItem(ProductDto product, int quantity) {
        return CartItem.builder()
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(quantity)
                .skuCode(product.getSkuCode())
                .build();
    }

    private Cart createCartForUser(String userId) {
        System.out.println("Creating new cart for user: " + userId);
        return Cart.builder()
                .userId(userId)
                .cartItems(new ArrayList<>())
                .build();
    }

    private void updateOrAddCartItem(Cart cart, CartItem item) {
        System.out.println("Updating or adding cart item: " + item);
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update quantity
            existingItem.get().setQuantity(existingItem.get().getQuantity() + item.getQuantity());
            System.out.println("Updated quantity for item: " + item);
        } else {
            // Add new item
            cart.getCartItems().add(item);
            System.out.println("Added new item to cart: " + item);
        }
    }

    public OrderResponse placeOrder(String userId, OrderRequestDto orderRequest) {
        // Log the received order request
        System.out.println("Sending request to Order Service: " + orderRequest);

        // Prepare the orderRequest object
        orderRequest.setUserId(userId);
        orderRequest.setOrderStatus(OrderStatus.PENDING); // Set initial order status

        try {
            // Send order request to Order Service
            OrderResponse orderResponse = webClient.post()
                .uri(ORDER_SERVICE_URL)  // Use the defined ORDER_SERVICE_URL
                .bodyValue(orderRequest)
                .retrieve()
                .bodyToMono(OrderResponse.class)
                .block(); // Block to wait for the response

            System.out.println("Order placed successfully for user: " + userId);
            return orderResponse;

        } catch (WebClientResponseException e) {
            // Handle errors from the order service
            System.err.println("Failed to place order: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to place order. Please try again later.");
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("Unexpected error while placing order: " + e.getMessage());
            throw new RuntimeException("An unexpected error occurred while placing the order.");
        }
    }

    // Remove an item from the cart
    public Cart removeItemFromCart(String userId, Long productId) {
        System.out.println("Removing item from cart for user: " + userId + ", product ID: " + productId);
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            System.err.println("Cart not found for user: " + userId);
            return null;
        }

        cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));
        System.out.println("Item removed from cart for user: " + userId);
        return cartRepository.save(cart);
    }

    // Clear the cart
    public void clearCart(String userId) {
        System.out.println("Clearing cart for user: " + userId);
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getCartItems().clear();
            cartRepository.save(cart);
            System.out.println("Cart cleared for user: " + userId);
        }
    }
}
