package com.example.demo;

import com.example.demo.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan( basePackages = { "com.example"})
@Import(CorsConfig.class)
@EnableScheduling
public class HackathonProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonProjectApplication.class, args);
	}

}
