package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		System.out.println("God is great .......... ");
	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Routes exposées par Spring Boot :");
			ctx.getBean(org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.class)
					.getHandlerMethods()
					.forEach((key, value) -> System.out.println(key));
		};
	}

}
