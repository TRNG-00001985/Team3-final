package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurtityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/jsp/home", "/jsp/products", "/jsp/profile","/jsp/productmanage", "/jsp/categories").permitAll() // Allow access to these paths
                        .requestMatchers("/jsp/cart", "/jsp/orders", "/jsp/login", "/jsp/register").authenticated() // Require auth for these
                        .anyRequest().permitAll()
                )
                .oauth2Login(login -> login
                        .successHandler(customAuthenticationSuccessHandler()) // Custom success handler for role-based redirection
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler()) // Custom Auth0 logout handler
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/jsp/home2")
                );

        http.addFilterAfter(new NoCacheFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Custom Authentication Success Handler
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = oidcUser.getAuthorities();
            OidcUserInfo userInfo = oidcUser.getUserInfo();
            
            
            // Log the roles/authorities assigned to the user for debugging
            System.out.println("User's authorities: ");
            for (GrantedAuthority authority : authorities) {
                System.out.println(authority.getAuthority());
            }
            System.out.println("role" + userInfo.getClaim("role"));

            // Default URL for redirection
            String redirectUrl = "/home1";
            if (userInfo.getClaim("role").toString().equals("[BUYERS]")) {
              redirectUrl = "/jsp/buyerdashboard";
          } else {
              redirectUrl = "/jsp/sellerdashboard";
          }
            
            System.out.println(redirectUrl);

            // Check the user's roles and redirect accordingly
//            for (GrantedAuthority authority : authorities) {
//                String role = authority.getAuthority();
//
//                if (role.equals("BUYERS")) {
//                    redirectUrl = "/buyerdashboard";
//                    break;
//                } else if (role.equals("SELLER")) {
//                    redirectUrl = "/sellerdashboard";
//                    break;
//                }
//            }

            response.sendRedirect(redirectUrl);
        };
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            String logoutUrl = "https://dev-nvbdyema8gq6gsle.us.auth0.com/v2/logout?client_id=DKDv1uLQiYx4Rq9Xkejxc25MLrt4teBd&returnTo=http://localhost/jsp/home2";
            response.sendRedirect(logoutUrl); // Redirect to Auth0 logout and then back to home
        };
    }
}
