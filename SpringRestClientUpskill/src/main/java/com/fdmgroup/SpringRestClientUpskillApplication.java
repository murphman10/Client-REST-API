package com.fdmgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringRestClientUpskillApplication {

	private final String CONTACT_REST_BASE_URL = "http://localhost:8080/api/v1/contacts";
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestClientUpskillApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
	
	@Bean
	public WebClient contactRestWebClient(WebClient.Builder builder) {
		
		return builder.baseUrl(CONTACT_REST_BASE_URL)
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.build();
	}

}
