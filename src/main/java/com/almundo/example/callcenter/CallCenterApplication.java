package com.almundo.example.callcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runs the application with the given parameters.
 *
 * Notes: In most exception surrounding a metric to a monitoring system like New Relic could be added.
 */
@SpringBootApplication
public class CallCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
	}
}
