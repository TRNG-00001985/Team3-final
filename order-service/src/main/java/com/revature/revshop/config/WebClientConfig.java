package com.revature.revshop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
public class WebClientConfig {

	

	@Autowired
	private ReactorLoadBalancerExchangeFilterFunction lbFilterFunction;
	
	@Bean
	public WebClient webClient() {
		return WebClient.builder().baseUrl("http://inventory-service").filter(lbFilterFunction).build();
	}
	

}