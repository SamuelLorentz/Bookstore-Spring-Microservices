package br.com.eureka.discovery.eurekabookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Classe de inicialização do microserviço.
 * 
 */
@EnableEurekaServer
@SpringBootApplication
public class Application {

	/**
	 * Metodo de inicialização do microserviço.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
