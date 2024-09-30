package com.revature.revshop.dto;


import com.revature.revshop.model.Category;

import jakarta.persistence.Column;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ProductResponse {
	
	    
	    private Long id;
	
        private String userId;
	    private String name;

	    private String description;

	   
	    private String skuCode;

	    private Double price;
	    
	 
	    private long categoryId;
	    private String imageurl;
	   

}

