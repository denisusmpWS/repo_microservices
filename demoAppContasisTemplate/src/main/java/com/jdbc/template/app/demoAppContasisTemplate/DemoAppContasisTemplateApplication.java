package com.jdbc.template.app.demoAppContasisTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class DemoAppContasisTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAppContasisTemplateApplication.class, args);
	}
}
