package br.com.bookstore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Shopping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date instant;

	private Customer customer;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "requests_has_books", joinColumns = { @JoinColumn(name = "request_id") }, inverseJoinColumns = {
			@JoinColumn(name = "book_id") })
	private List<Book> books;

	private String status;

	public Shopping() {

	}

	public Shopping(Date instant, Customer customer, String status, List<Book> books) {
		this.instant = instant;
		this.customer = customer;
		this.status = status;
		this.books = books;
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
