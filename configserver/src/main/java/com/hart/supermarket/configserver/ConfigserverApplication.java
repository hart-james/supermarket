package com.hart.supermarket.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

//Youtube Playlist for Config Server
//https://www.youtube.com/watch?v=gb1i4WyWNK4
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
		System.out.println("Config Server.");
	}

}
