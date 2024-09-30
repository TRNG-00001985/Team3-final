package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Auth0demoApplication extends 
	SpringBootServletInitializer {

	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(Auth0demoApplication.class);
	    }
	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(Auth0demoApplication.class, args);
	    }

	}


