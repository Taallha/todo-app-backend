package com.project.todorestfulwebservices;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
	
	// Create Filter chain 
	//authenticate all requests
	//Basic authentication 
	//disabiling csrf
	//stataless rest api
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
				auth -> auth
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.csrf().disable()
		.build();
		
	}
}
