package com.revature.revshop.dto;

import java.util.List;

import com.revature.revshop.model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private String userId;
    private OrderStatus status;
    private List<OrderLineItemsDto> orderLineItems; // Use your existing OrderLineItemDTO
    private String billingAddress;
    private String shippingAddress;
    private OrderStatus orderStatus; // Add order status

}