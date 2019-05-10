package br.com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bookstore.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
