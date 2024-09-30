package com.example.demo.config;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class Auth0JwtFilter extends OncePerRequestFilter {
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
//            throws ServletException, IOException {
//        // Extract JWT from request header (Authorization)
//        String token = getJwtFromRequest(request);
//
//        if (token != null && validateJwtToken(token)) {
//        	try {
//                String email = extractEmailFromToken(token);
//                UserDetails userDetails = createUserDetailsFromEmail(email);
//                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
//            } catch (Exception e) {
//                logger.error("Error extracting user details from token", e);
//                // Optionally, set the response status here if desired
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7); // Remove "Bearer " from token
//        }
//        return null;
//    }
//
//    private boolean validateJwtToken(String token) {
//        // Logic to validate the JWT token (this can be done using JWT libraries or manually)
//        return true; // For now, we assume it's valid
//    }
//
//    private String extractEmailFromToken(String token) {
//        // Use Auth0 JWT library to decode token and extract claims
//        DecodedJWT decodedJWT = JWT.decode(token);
//        return decodedJWT.getClaim("email").asString(); // Extract the "email" claim
//    }
//
//    private UserDetails createUserDetailsFromEmail(String email) {
//        // Create UserDetails object with email
//        return User.withUsername(email).password("").authorities("ROLE_USER").build();
//    }
//
//	@Override
//	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
//			throws jakarta.servlet.ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//	}
//}
//


//import com.auth0.jwt.JWT;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class Auth0JwtFilter extends OncePerRequestFilter {
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//	        throws ServletException, IOException {
//	    
//	    String token = getJwtFromRequest(request);
//	    
//	    if (token != null) {
//	        System.out.println("JWT Token found: " + token); // Log token
//	        if (validateJwtToken(token)) {
//	            String email = extractEmailFromToken(token);
//	            System.out.println("Extracted email: " + email); // Log email
//	            UserDetails userDetails = createUserDetailsFromEmail(email);
//	            
//	            // Set Authentication in SecurityContextHolder
//	            SecurityContextHolder.getContext().setAuthentication(
//	                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
//	        } else {
//	            System.out.println("Invalid JWT token");
//	        }
//	    } else {
//	        System.out.println("No JWT token found in request");
//	    }
//
//	    filterChain.doFilter(request, response); // Pass to next filter
//	}
//
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7); // Remove "Bearer " from token
//        }
//        return null;
//    }
//
//    private boolean validateJwtToken(String token) {
//        return true; // Add your validation logic here
//    }
//
//    private String extractEmailFromToken(String token) {
//        DecodedJWT decodedJWT = JWT.decode(token);
//        String email = decodedJWT.getClaim("email").asString();
//        System.out.println("Extracted email: " + email); // Log the email
//        return email; 
//    }
//
//
//    private UserDetails createUserDetailsFromEmail(String email) {
//        return User.withUsername(email).password("").authorities("ROLE_USER").build();
//    }
//
//	
//}
