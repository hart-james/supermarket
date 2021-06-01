package com.hart.supermarket.item;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemApplication {


	public static void main(String[] args) {

		SpringApplication.run(ItemApplication.class, args);
		System.out.println("Items Service.");
	}

	@Bean
	RateLimiter rateLimiter(){
		return RateLimiter.create(
				2,
				Duration.ofSeconds(10));
	}
}
