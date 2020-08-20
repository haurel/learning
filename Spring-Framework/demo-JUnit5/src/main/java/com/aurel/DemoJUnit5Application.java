package com.aurel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class DemoJUnit5Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoJUnit5Application.class, args);
	}

}
