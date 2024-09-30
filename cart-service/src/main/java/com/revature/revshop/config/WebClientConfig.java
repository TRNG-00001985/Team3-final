package com.revature.revshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
@Configuration
public class WebClientConfig {

    @Autowired
	private ReactorLoadBalancerExchangeFilterFunction lbFilterFunction;
	
	@Bean
	public WebClient webClient() {
		return WebClient.builder().baseUrl("http://product-service").filter(lbFilterFunction).build();
	}
}
