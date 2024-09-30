package com.example.demo.controller;



import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/jsp")
public class EcommerceController {

    @GetMapping("/home")
    public String home() {
    	System.out.println("home");
            return "home";  
    }
    @GetMapping("/home1")
    public String home1() {
            return "home1";  
    }
    @GetMapping("/updateProduct")
    public String updateProduct() {
            return "updateProduct";  
    }
    @GetMapping("/ProductManager")
    public String ProductManager() {
            return "ProductManager";  
    }
    @GetMapping("/buyerdashboard")
    public String buyerdashboard() {
            return "buyerdashboard";  
    }
    @GetMapping("/sellerdashboard")
    public String sellerdashboard() {
            return "sellerdashboard";  
    }
    
   
    
    @GetMapping("/home2")
    public String home2() {
            return "home2";  
    }
    @GetMapping("/createProduct")
    	 public String createProduct(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
    	        if (oidcUser == null) {
    	            // User is not authenticated, redirect to login or return an error page
    	            return "redirect:/jsp/login";  // Redirect to login page if not authenticated
    	        }

    	        model.addAttribute("profile", oidcUser.getClaims());
    	        

    	        OidcIdToken idToken = oidcUser.getIdToken();
    	     
    	        System.out.println("ID Token Subject: " + idToken.getSubject());

    	         // Return the profile view
            return "createProduct";  
    }
    
    @GetMapping("/viewOrders")
    public String viewOrders(Model model, @AuthenticationPrincipal OidcUser oidcUser, HttpSession session) {
        if (oidcUser == null) {
            return "redirect:/jsp/login";  // Redirect to login page if not authenticated
        }
        
        String userId = oidcUser.getIdToken().getSubject();
        System.out.println("Fetching orders for user ID: " + userId);

        // Store userId in the session
        session.setAttribute("userId", userId);

        // Retrieve user ID from the OIDC token
        System.out.println("Fetching orders for user ID: " + userId);

        // Fetch orders for the user

        return "viewOrders";  
    }
    
    @GetMapping("/ProdByuser")
	 public String ProdByuser(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
	        if (oidcUser == null) {
	            // User is not authenticated, redirect to login or return an error page
	            return "redirect:/jsp/login";  // Redirect to login page if not authenticated
	        }

	        model.addAttribute("profile", oidcUser.getClaims());
	        

	        OidcIdToken idToken = oidcUser.getIdToken();
	     
	        System.out.println("ID Token Subject: " + idToken.getSubject());

	         // Return the profile view
       return "ProdByuser";  
}

    @GetMapping("/categories")
    public String categories() {
        return "categories"; 
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";  
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";  
    }

    @GetMapping("/login")
    public String login() {
        return "login";  
    }

 
    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
        if (oidcUser == null) {
            // User is not authenticated, redirect to login or return an error page
            return "redirect:/jsp/login";  // Redirect to login page if not authenticated
        }

        model.addAttribute("profile", oidcUser.getClaims());
        
        if (oidcUser.getUserInfo() != null) {
            OidcUserInfo userInfo = oidcUser.getUserInfo();
            System.out.println("User Info Claims: " + userInfo.getClaims());
            System.out.println("User Email: " + userInfo.getEmail());
            System.out.println("User Name: " + userInfo.getFullName());
            System.out.println("User Gender: " + userInfo.getGender());
        } else {
            System.out.println("No UserInfo available");
        }

        OidcIdToken idToken = oidcUser.getIdToken();
        System.out.println("ID Token Claims: " + idToken.getClaims());
        System.out.println("ID Token Subject: " + idToken.getSubject());
        System.out.println("ID Token Issuer: " + idToken.getIssuer());
        System.out.println(idToken.getTokenValue());

        return "profile";  // Return the profile view
    }


}

