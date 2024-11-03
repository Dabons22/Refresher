package com.refresher.refresher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.refresher.refresher")
public class RefresherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefresherApplication.class, args);
	}

}
