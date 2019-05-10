package br.com.credit;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import br.com.credit.model.CreditCard;
import br.com.credit.repository.CreditCardRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class CreditTransactionApiApplication implements CommandLineRunner {

	@Autowired
	CreditCardRepository CreditCardRepository;

	public static void main(String[] args) {
		SpringApplication.run(CreditTransactionApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		CreditCard cus1 = new CreditCard(null, "VISA", 123, localDate, 56984785);
		CreditCardRepository.saveAll(Arrays.asList(cus1));
	}
}
