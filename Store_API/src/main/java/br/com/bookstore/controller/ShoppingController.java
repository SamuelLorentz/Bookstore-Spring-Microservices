package br.com.bookstore.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.bookstore.model.Shopping;
import br.com.bookstore.model.dto.BasketDTO;
import br.com.bookstore.model.dto.ShoppingDTO;
import br.com.bookstore.service.ShoppingService;

@RestController
@RequestMapping(value = "/v1/shopping")
public class ShoppingController {

	private final ShoppingService shoppingService;

	@Autowired
	public ShoppingController(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

	@GetMapping
	public ResponseEntity<List<Shopping>> getOrders() {
		List<Shopping> list = (List<Shopping>) shoppingService.findAllShoppings();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Shopping> getOrderById(@PathVariable Integer id) {
		Shopping obj = shoppingService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<List<Shopping>> findOrdersByStatus(@PathVariable Integer status) {
		List<Shopping> list = shoppingService.findShoppingsByStatus(status);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Shopping> insert(@Validated @RequestBody BasketDTO basketDTO) {
		Shopping shopping = shoppingService.insertShopping(new Date(), basketDTO, "NEW");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shopping.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public Shopping updateOrder(@PathVariable Integer id, @RequestBody ShoppingDTO shoppingDTO) {
		return shoppingService.update(shoppingDTO);
	}

}
