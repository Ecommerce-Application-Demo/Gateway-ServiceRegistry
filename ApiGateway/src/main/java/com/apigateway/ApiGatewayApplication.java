package com.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Value("${render-server}")
	String baseUri;

	@Scheduled(fixedDelay = 1000*60*5)
	void renderRunner() {
		RestTemplate restTemplate= new RestTemplate();
		restTemplate.getForEntity(baseUri,String.class);
	}

}
