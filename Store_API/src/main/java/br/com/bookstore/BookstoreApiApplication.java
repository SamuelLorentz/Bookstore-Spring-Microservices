package br.com.bookstore;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import br.com.bookstore.model.Basket;
import br.com.bookstore.model.Book;
import br.com.bookstore.model.BookComment;
import br.com.bookstore.model.Customer;
import br.com.bookstore.model.Shopping;
import br.com.bookstore.repository.BasketRepository;
import br.com.bookstore.repository.BookRepository;
import br.com.bookstore.repository.OrderRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class BookstoreApiApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	BasketRepository basketRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// -- books
		Book book1 = new Book("978-9851875120", "Microservices PUC", "PUC", 25.0);
		Book book2 = new Book("978-0321356680", "Effective Java", "Addison Wesley", 35.00);
		Book book3 = new Book("978-1617292545", "Spring Boot in Action", "Manning Publications", 45.00);
		Book book4 = new Book("978-1491900864", "Java 8 Pocket Guide", "O'Reilly", 55.00);
		Book book5 = new Book("978-0321349606", "Java Concurrency in Practice", "Addison Wesley", 15.00);

		// -- comments
		book1.addComment(new BookComment("Samuel", "My first review"));
		book1.addComment(new BookComment("Edson", "Interesting"));
		book2.addComment(new BookComment("Rodrigo", "Nice!!"));
		book3.addComment(new BookComment("Luisa", "Not good"));

		bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5));

		Customer cus1 = new Customer(null, "Samuel", "email@email", "5555555555");

		Basket bas1 = new Basket(cus1, Arrays.asList(book1, book2));
		basketRepository.saveAll(Arrays.asList(bas1));

		Shopping req1 = new Shopping(new Date(), cus1, "NEW", Arrays.asList(book1, book2));
		Shopping req2 = new Shopping(new Date(), cus1, "Delivered", Arrays.asList(book2));
		orderRepository.saveAll(Arrays.asList(req1, req2));

	}

}
