package com.example.gara_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableConfigurationProperties
@EnableFeignClients
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class GaraManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaraManagementApplication.class, args);
	}

}
