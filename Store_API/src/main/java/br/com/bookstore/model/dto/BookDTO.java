package br.com.bookstore.model.dto;

import java.io.Serializable;
import java.util.List;

import br.com.bookstore.model.Book;
import br.com.bookstore.model.BookComment;

public class BookDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String isbn;
	private String title;
	private String author;
	private Double price;
	private List<BookComment> comments;
	
	public BookDTO(){
		
	}
	
	public BookDTO(Book obj) {
		this.id = obj.getId();
		this.isbn = obj.getIsbn();
		this.author = obj.getAuthor();
		this.title = obj.getTitle();
		this.price = obj.getPrice();
		this.comments = obj.getCommentaries();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public List<BookComment> getCommentaries() {
		return comments;
	}

	public void setCommentaries(List<BookComment> commentaries) {
		this.comments = commentaries;
	}

}
