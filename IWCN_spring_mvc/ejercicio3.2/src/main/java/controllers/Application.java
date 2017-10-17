package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import services.ProductService;

@SpringBootApplication
public class Application {

	@Bean
	public ProductService productService(){
		return new ProductService();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}