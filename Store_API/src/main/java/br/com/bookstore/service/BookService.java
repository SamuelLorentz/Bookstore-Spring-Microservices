package br.com.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookstore.model.Book;
import br.com.bookstore.model.BookComment;
import br.com.bookstore.model.dto.BookDTO;
import br.com.bookstore.repository.BookRepository;
import br.com.bookstore.service.exception.ObjectNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Find book by parameters.
	 * 
	 * @param id
	 * @param isbn
	 * @param author
	 * @param title
	 * @param price
	 * @return
	 */
	public Iterable<BookDTO> findBookByParameters(Integer id, String isbn, String author, String title, Double price) {
		List<Book> list = findBooks(id, isbn, author, title, price);
		List<BookDTO> listDTO = new ArrayList<>();
		for (Book book : list) {
			listDTO.add(new BookDTO(book));
		}
		return listDTO;
	}

	/**
	 * Find book by id.
	 * 
	 * @param id
	 * @return
	 */
	public BookDTO findById(Integer id) {
		Optional<Book> obj = getRepository().findById(id);
		Book book = obj.orElseThrow(() -> new ObjectNotFoundException(getExceptionMessage(id)));
		return new BookDTO(book);
	}

	/**
	 * Return all books.
	 * 
	 * @return
	 */
	public Iterable<BookDTO> findAllBooks() {
		List<BookDTO> bookDTO = new ArrayList<>();
		List<Book> books = (List<Book>) getRepository().findAll();
		for (Book book : books) {
			bookDTO.add(new BookDTO(book));
		}
        return bookDTO;
	}
	
	/**
	 * Insert new book.
	 * 
	 * @param obj
	 * @return
	 */
	public Book insert(Book obj) {
		return getRepository().save(obj);
	}
	
	/**
	 * Add comment on a book.
	 * 
	 * @param id
	 * @param comment
	 * @return
	 */
	public Book insertCommentary(Integer id, BookComment comment) {
		Optional<Book> obj = getRepository().findById(id);
		Book book = obj.orElseThrow(() -> new ObjectNotFoundException(getExceptionMessage(id)));
		book.addComment(comment);
		return getRepository().save(book);
	}

	/**
	 * Update book.
	 * 
	 * @param obj
	 * @return
	 */
	public BookDTO update(Book obj, Integer id) {
		Book newObj = fromDTO(findById(id));
		newObj.setId(id);
		updateBook(newObj, obj);
		return new BookDTO(getRepository().save(newObj));
	}

	private void updateBook(Book newObj, Book obj) {
		newObj.setAuthor(obj.getAuthor());
		newObj.setTitle(obj.getTitle());
		newObj.setPrice(obj.getPrice());
		newObj.setCommentaries(obj.getCommentaries());
	}

	/**
	 * delete book by id.
	 * 
	 * @param id
	 */
	public void deleteBookById(Integer id) {
		getRepository().deleteById(id);
	}

	public Book fromDTO(BookDTO objDTO) {
		return new Book(objDTO.getIsbn(), objDTO.getTitle(), objDTO.getAuthor(), objDTO.getPrice());
	}

	private String getExceptionMessage(Integer id) {
		return "Objeto not found! Id: " + id + ", Kind: " + Book.class.getName();
	}

	public BookRepository getRepository() {
		return bookRepository;
	}
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Book> findBooks(Integer id, String isbn, String author, String title, Double price) {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT l FROM Book l WHERE l.id is not null AND ");

		if (isbn != null) {
			sb.append("l.isbn = '" + isbn + "' AND ");
		}
		if (author != null) {
			sb.append("l.author = '" + author + "' AND ");
		}
		if (id != null) {
			sb.append("l.id = '" + id + "' AND ");
		}
		if (title != null) {
			sb.append("l.title = '" + title + "' AND ");
		}
		if (price != null) {
			sb.append("l.price = " + price + " AND ");
		}

		sb.append(" 1 = 1 ");

		Query query = em.createQuery(sb.toString());
		List<Book> results = query.getResultList();
		return results;
	}

}
