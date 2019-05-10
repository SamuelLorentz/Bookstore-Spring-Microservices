package br.com.eureka.discovery.eurekabookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaBookstoreApplication {
   public static void main(String[] args) {
      SpringApplication.run(EurekaBookstoreApplication.class, args);
   }
}