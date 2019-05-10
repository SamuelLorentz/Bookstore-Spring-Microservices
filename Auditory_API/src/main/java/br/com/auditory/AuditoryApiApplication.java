package br.com.auditory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuditoryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditoryApiApplication.class, args);
	}

}
