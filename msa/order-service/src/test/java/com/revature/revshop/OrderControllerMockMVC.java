package com.revature.revshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revshop.controller.OrderController;
import com.revature.revshop.dto.OrderRequest;
import com.revature.revshop.model.Order;
import com.revature.revshop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private OrderRequest orderRequest;
    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize test data
        orderRequest = new OrderRequest();
        orderRequest.setUserId("12345");
        orderRequest.setBillingAddress("123 Main St");
        orderRequest.setShippingAddress("456 Maple St");
        // Set up other fields for OrderRequest...

        order = new Order();
        order.setOrderNumber("order123");
        order.setUserId("12345");
        order.setBillingAddress("123 Main St");
        order.setShippingAddress("456 Maple St");
        // Set up other fields for Order...
    }

  

    @Test
    void testGetOrdersByUserId() throws Exception {
        // Mock the service response
        when(orderService.getOrdersByUserId("12345")).thenReturn(List.of(order));

        // Perform the GET request
        mockMvc.perform(get("/api/order/user")
                        .param("userId", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber").value("order123"));
    }

    @Test
    void testDeleteOrder() throws Exception {
        // Mock the service response
        when(orderService.deleteOrder(1L)).thenReturn("Order deleted successfully");

        // Perform the DELETE request
        mockMvc.perform(delete("/api/order/delete")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order deleted successfully"));
    }

    @Test
    void testUpdateOrder() throws Exception {
        // Mock the service response
        when(orderService.updateOrder(any(Long.class), any(OrderRequest.class)))
                .thenReturn("Order updated successfully");

        // Perform the PUT request
        mockMvc.perform(put("/api/order/update")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order updated successfully"));
    }

    @Test
    void testGetOrdersByStatus() throws Exception {
        // Mock the service response
        when(orderService.getOrdersByStatus("PENDING")).thenReturn(Collections.singletonList(order));

        // Perform the GET request
        mockMvc.perform(get("/api/order/status/PENDING")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber").value("order123"));
    }
}
