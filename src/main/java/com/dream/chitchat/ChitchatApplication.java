package com.dream.chitchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@SpringBootApplication
@EnableScheduling
public class ChitchatApplication {
	private static final Logger logger = LoggerFactory.getLogger(ChitchatApplication.class);
   
	public static void main(String[] args) {
		logger.info("Chit Chat application started.");
		SpringApplication.run(ChitchatApplication.class, args);
	}
	@SuppressWarnings("deprecation")
	@Bean
	   public WebMvcConfigurer corsConfigurer() {
	      return new WebMvcConfigurerAdapter() {
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/products").allowedOrigins("http://localhost:8080");
	         }
	      };
	   }
	@RequestMapping("/")
	public String hello() {
		return "welcome to chitchat client";
	}

}
