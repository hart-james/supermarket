package com.hart.supermarket.Gateway2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class Gateway2Application {

	public static void main(String[] args) {
		SpringApplication.run(Gateway2Application.class, args);
		System.out.println("Gateway Service West");
	}

}
