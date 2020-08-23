package com.sii.cqrs;

import org.axonframework.extensions.kafka.autoconfig.KafkaAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
