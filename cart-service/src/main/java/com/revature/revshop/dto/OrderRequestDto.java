package com.revature.revshop.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
	
	private List<OrderLineItemsDto> orderLineItems;
	private String userId;
	
	private String billingAddress; // Add billing address
	private String shippingAddress;
    private OrderStatus orderStatus; // Add order status


}
