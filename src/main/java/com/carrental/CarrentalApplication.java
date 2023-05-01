package com.carrental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CarrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrentalApplication.class, args);
	}

	// @Bean
	// CommandLineRunner commandLineRunner(Environment environment){
	// 	return args -> {
	// 		System.out.println(environment.toString());
	// 		String databaseHost = environment.getProperty("DATABASE_HOST");
	// 		String databasePort = environment.getProperty("DATABASE_PORT");
	// 		String databaseName = environment.getProperty("DATABASE_NAME");
	// 		String databaseUsername = environment.getProperty("DATABASE_USERNAME");
	// 		String databasePassword = environment.getProperty("DATABASE_PASSWORD");

	// 		System.out.println("DATABASE_HOST: " + databaseHost);
	// 		System.out.println("DATABASE_PORT: " + databasePort);
	// 		System.out.println("DATABASE_NAME: " + databaseName);
	// 		System.out.println("DATABASE_USERNAME: " + databaseUsername);
	// 		System.out.println("DATABASE_PASSWORD: " + databasePassword);
	// 	};
	// }
}
