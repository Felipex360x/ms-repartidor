package com.tiendacarta.repartidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.tiendacarta.repartidor.Client")
public class RepartidorApplication {
	public static void main(String[] args) {
		SpringApplication.run(RepartidorApplication.class, args);
	}

}
