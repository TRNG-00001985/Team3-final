package com.revature.revshop.dto;

import java.util.List;


import lombok.Data;

@Data
public class CartRequest {
    private Long userId;                       // ID of the user
    private List<CartItemDto> cartItems;         // List of items to be added to the cart

}
