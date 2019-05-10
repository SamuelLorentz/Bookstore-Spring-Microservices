package br.com.bookstore.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bookstore.model.dto.BasketDTO;
import br.com.bookstore.model.dto.BookDTO;
import br.com.bookstore.service.BasketService;

@RestController
@RequestMapping(value = "/v1/baskets")
public class BasketController {

	@Autowired
	private BasketService basketService;

	@GetMapping
	public ResponseEntity<List<BasketDTO>> findAll() {
		List<BasketDTO> listDTO = getService().findAllBaskets();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BasketDTO> getBasketById(@PathVariable Integer id) {
		BasketDTO basketDTO = getService().findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(basketDTO);
	}
	
	@PostMapping
	public ResponseEntity<BasketDTO> insertBasket(@Validated @RequestBody BasketDTO basketDTO) {
		getService().insertBasket(basketDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(basketDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}/book")
	public ResponseEntity<BasketDTO> insertBookBasket(@PathVariable("id") Integer id,
			@Validated @RequestBody BookDTO bookDTO) {
		BasketDTO obj =  getService().insertBookBasket(id, bookDTO.getId());
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Validated @RequestBody BasketDTO objDto) {
		getService().updateBasket(objDto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/clean/{id}")
	public void cleanBasket(@PathVariable("id") Integer id) {
		getService().cleanBasket(id);
	}
	
	private BasketService getService() {
		return basketService;
	}

}
