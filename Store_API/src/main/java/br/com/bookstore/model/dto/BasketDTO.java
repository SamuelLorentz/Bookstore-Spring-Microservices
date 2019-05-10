package br.com.bookstore.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bookstore.model.Basket;
import br.com.bookstore.model.Book;
import br.com.bookstore.model.Customer;

public class BasketDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonIgnore
	private Customer customer;
	private String customerToken;
	private List<Book> books = new ArrayList<>();
	
	public BasketDTO() {
		
	}
	
	public BasketDTO(Basket obj, String customerToken) {
		this.id = obj.getId();
		this.customer = obj.getCustomer();
		this.customerToken = customerToken;
		this.books = obj.getBooks();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}
	
}
