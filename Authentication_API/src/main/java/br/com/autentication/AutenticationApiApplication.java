package br.com.autentication;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import br.com.autentication.model.Customer;
import br.com.autentication.repository.CustomerRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class AutenticationApiApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository CustomerRepository;
	
	public static void main(String[] args) {
        SpringApplication.run(AutenticationApiApplication.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		Customer cus1 = new Customer(null, "Samuel", "email@email", "5555555555", "pwd123");
		CustomerRepository.saveAll(Arrays.asList(cus1));
	}

}
