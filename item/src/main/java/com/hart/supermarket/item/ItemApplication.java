package com.hart.supermarket.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemApplication {


	public static void main(String[] args) {

		SpringApplication.run(ItemApplication.class, args);
		System.out.println("Items Service.");
	}

}
