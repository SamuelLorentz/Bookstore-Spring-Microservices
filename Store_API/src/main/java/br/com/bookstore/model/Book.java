package br.com.bookstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String isbn;

	@NotBlank
	private String title;

	@NotBlank
	private String author;

	private Double price;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<BookComment> comments;

	public Book() {

	}

	public Book(String isbn, String author, String title, Double price, List<BookComment> comments) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.comments = comments;
	}
	
	public Book(String isbn, String author, String title, Double price) {
        this(isbn, author, title, price, new ArrayList<>());
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

	public void addComment(BookComment comment) {
        this.comments.add(comment);
    }
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("isbn", isbn)
				.append("title", title)
				.append("author", author)
				.append("price", price)
				.append("comments", comments)
				.toString();
	}
}
