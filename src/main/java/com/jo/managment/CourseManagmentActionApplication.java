package com.jo.managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com" })
@EnableAsync
@EnableCaching
@EnableEurekaClient
public class CourseManagmentActionApplication {

	public static void main(String[] args) {

		SpringApplication.run(CourseManagmentActionApplication.class, args);
	}

}
