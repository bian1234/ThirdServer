package com.prostate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableHystrix  //@EnableCircuitBreaker  ==  @EnableHystrix
@EnableEurekaServer
@SpringBootApplication
@EnableCaching
public class ThirdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdApplication.class, args);
	}
}
