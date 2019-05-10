package br.com.bookstore.model.dto;

import java.util.Date;
import java.util.List;

import br.com.bookstore.model.Book;
import br.com.bookstore.model.Customer;
import br.com.bookstore.model.Shopping;

public class ShoppingDTO {

	private Integer id;
	private Date instant;
	private Customer customer;
	private List<Book> books;
	private String status;
	
	public ShoppingDTO(){
		
	}
	
	public ShoppingDTO(Shopping obj) {
		this.id = obj.getId();
		this.instant = obj.getInstant();
		this.customer = obj.getCustomer();
		this.status = obj.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
