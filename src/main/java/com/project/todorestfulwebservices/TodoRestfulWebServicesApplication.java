package com.project.todorestfulwebservices;

import java.rmi.registry.Registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TodoRestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoRestfulWebServicesApplication.class, args);
	}
	
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("*")
					.allowedOrigins("http://localhost:3000")
					.allowCredentials(true);
			}
			
		};
	}

}
