package com.revature.revshop.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.revature.revshop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revshop.dto.OrderLineItemsDto;
import com.revature.revshop.dto.OrderRequest;
import com.revature.revshop.event.OrderEvent;
import com.revature.revshop.model.Order;
import com.revature.revshop.model.OrderLineItems;
import com.revature.revshop.model.OrderStatus;
import com.revature.revshop.repository.OrderLineItemsRepository;
import com.revature.revshop.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final OrderLineItemsRepository orderLineItemsRepository;
	
	private final WebClient webClient;
	
	private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	@Autowired
	public OrderService(OrderRepository orderRepository, OrderLineItemsRepository orderLineItemsRepository, WebClient webClient, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
		this.orderRepository=orderRepository;
		this.orderLineItemsRepository= orderLineItemsRepository;
		this.webClient = webClient;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .name(orderLineItemsDto.getName()) // Ensure to set the name as well
                .build();
    }

	public String placeOrder(OrderRequest orderRequest) {
	    System.out.println("Received Order Request: " + orderRequest);  // Log the order request

	    List<Boolean> itemsInStock = itemsInStock(orderRequest.getOrderLineItems());
	    
	    for (Boolean inStock : itemsInStock) {
	        if (!inStock) {
	            return "Some items are not in stock";
	        }
	    }

	    Order order = new Order();
	    order.setOrderNumber(UUID.randomUUID().toString());
	    order.setOrderLineItems(
	        orderRequest.getOrderLineItems().stream()
	            .map(this::mapToOrderLineItems)
	            .toList()
	    );
	    order.setUserId(orderRequest.getUserId());
	    order.setBillingAddress(orderRequest.getBillingAddress());  // Set billing address
	    order.setShippingAddress(orderRequest.getShippingAddress());  // Set shipping address
	    order.setStatus(orderRequest.getOrderStatus());
	    
	    System.out.println("Saving Order: " + order);  // Log the order before saving
	    orderRepository.save(order);

	    // Publish the order event to Kafka
	    kafkaTemplate.send("notificationTopic", new OrderEvent(this, order.getOrderNumber(), order.getUserId(), order.getStatus()));
	    System.out.println("Notification sent");

	    return "Order placed successfully with order number: " + order.getOrderNumber();  // Return success message
	}


	
	public List<Boolean> itemsInStock(List<OrderLineItemsDto> list) {
		
		try {
			
			Mono<List<Boolean>> itemStatus = webClient.post()
					.uri("instock/")
					.bodyValue(list)
					.retrieve()
					.bodyToMono( new ParameterizedTypeReference<List<Boolean>>() {});
			
			return itemStatus.block();
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return null;
			
		}

	}
	
	// New method to get orders by user ID
		public List<Order> getOrdersByUserId(String userId) {
			return orderRepository.findByUserId(userId);
		}

		public String deleteOrder(Long id) {
		    Order existingOrder = orderRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Order not found"));
		    orderRepository.delete(existingOrder);
		    return "Order deleted successfully";
		}

		public String updateOrder(Long id, OrderRequest orderRequest) {
	        // Fetch existing order
	        Order existingOrder = orderRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found"));

	        // Clear existing line items if needed
	        existingOrder.getOrderLineItems().clear();

	        // Update existing order details if necessary
	        existingOrder.setUserId(orderRequest.getUserId());
	        existingOrder.setBillingAddress(orderRequest.getBillingAddress());
	        existingOrder.setShippingAddress(orderRequest.getShippingAddress());
	        existingOrder.setStatus(orderRequest.getOrderStatus());

	        // Map and add new order line items
	        List<OrderLineItems> newLineItems = orderRequest.getOrderLineItems().stream()
	                .map(this::mapToOrderLineItems)
	                .collect(Collectors.toList());
	        existingOrder.getOrderLineItems().addAll(newLineItems);

	        // Save updated order
	        orderRepository.save(existingOrder);

	        return "Order updated successfully";
	    }

		// Method to get orders by status
		public List<Order> getOrdersByStatus(String status) {
			return orderRepository.findByStatus(status);
		}

		// Method to get order line items by order ID
		
}
