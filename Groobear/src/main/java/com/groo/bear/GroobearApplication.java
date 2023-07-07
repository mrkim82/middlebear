package com.groo.bear;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.groo.bear.**.mapper")
public class GroobearApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroobearApplication.class, args);
	}
	//
}
