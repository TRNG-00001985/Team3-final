package com.revature.revshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



import com.revature.revshop.dto.OrderRequest;
import com.revature.revshop.event.OrderEvent;
import com.revature.revshop.model.Order;

import com.revature.revshop.repository.OrderLineItemsRepository;
import com.revature.revshop.repository.OrderRepository;
import com.revature.revshop.service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.reactive.function.client.WebClient;

class OrderServiceApplicationTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderLineItemsRepository orderLineItemsRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 



    @Test
    void testGetOrdersByUserId() {
        // Arrange
        String userId = "12345";
        List<Order> mockOrders = Arrays.asList(new Order());
        when(orderRepository.findByUserId(userId)).thenReturn(mockOrders);

        // Act
        List<Order> orders = orderService.getOrdersByUserId(userId);

        // Assert
        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testDeleteOrder() {
        // Arrange
        Long orderId = 1L;
        Order mockOrder = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        // Act
        String result = orderService.deleteOrder(orderId);

        // Assert
        assertEquals("Order deleted successfully", result);
        verify(orderRepository, times(1)).delete(mockOrder);
    }

  

    @Test
    void testGetOrdersByStatus() {
        // Arrange
        String status = "PLACED";
        List<Order> mockOrders = Arrays.asList(new Order());
        when(orderRepository.findByStatus(status)).thenReturn(mockOrders);

        // Act
        List<Order> orders = orderService.getOrdersByStatus(status);

        // Assert
        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findByStatus(status);
    }
}
