package com.revature.revshop.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String skuCode;
    private Double price;
    // Add other relevant fields as necessary
}
