package com.hart.supermarket.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SalesApplication {


	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
		System.out.println("Sales Service.");
	}

}