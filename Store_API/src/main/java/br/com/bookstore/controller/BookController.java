package br.com.bookstore.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bookstore.model.Book;
import br.com.bookstore.model.BookComment;
import br.com.bookstore.model.dto.BookDTO;
import br.com.bookstore.service.BookService;

@RestController
@RequestMapping(value = "/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> getBookByParameters(
															@RequestParam(required = false) Integer id,
															@RequestParam(required = false) String isbn,
															@RequestParam(required = false) String title, 
															@RequestParam(required = false) String author,
															@RequestParam(required = false) Double price) {
		List<BookDTO> listDTO = (List<BookDTO>) bookService.findBookByParameters(id, isbn, title, author, price);
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<Void> insertBook(@Validated @RequestBody BookDTO objDto) {
		Book obj = bookService.fromDTO(objDto);
		obj = bookService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BookDTO> update(@Validated @RequestBody BookDTO objDto, @PathVariable Integer id) {
		Book obj = bookService.fromDTO(objDto);
		bookService.update(obj, id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}/commentary")
	public Book commentBook(@PathVariable("id") Integer id, @RequestBody BookComment commentary) {
		return bookService.insertCommentary(id, commentary);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteBook(@PathVariable("id") Integer bookId) {
		bookService.deleteBookById(bookId);
	}

}
