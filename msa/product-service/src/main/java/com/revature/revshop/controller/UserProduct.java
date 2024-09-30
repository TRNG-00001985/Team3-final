package com.revature.revshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.revshop.dto.ProductResponse;
import com.revature.revshop.model.Product;
import com.revature.revshop.service.ProductService;
@RequestMapping("/user")
@Controller

public class UserProduct {

    private final ProductService productService;

    @Autowired
    public UserProduct(ProductService productService) {
        this.productService = productService;
    }

   
    @GetMapping("/products")
    public String getUserProducts(@RequestParam(required = false) String search, Model model) {
        List<ProductResponse> productResponses;

        if (search != null && !search.trim().isEmpty()) {
            productResponses = productService.searchProducts(search);
        } else {
            productResponses = productService.getAllProducts();
        }

        model.addAttribute("products", productResponses);
        model.addAttribute("search", search);
        return "UserProduct";
    }
    @GetMapping("/searchProduct")
    public String searchProducts(@RequestParam String query, Model model) {
        model.addAttribute("products", productService.searchProducts(query));
        return "UserProduct"; // Points to productList.jsp with search results
    }
}
