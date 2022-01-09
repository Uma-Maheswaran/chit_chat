package com.dream.chitchat.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class ConsumerWebServiceDI {
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
