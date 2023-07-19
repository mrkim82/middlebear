package com.groo.bear;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan(basePackages = "com.groo.bear.**.mapper")
public class GroobearApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GroobearApplication.class, args);
	}
}
