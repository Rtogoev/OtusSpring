package ru.otus.hw18Hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCircuitBreaker
@EnableMongoRepositories
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
