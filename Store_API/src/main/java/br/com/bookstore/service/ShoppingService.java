package br.com.bookstore.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookstore.model.Shopping;
import br.com.bookstore.model.dto.BasketDTO;
import br.com.bookstore.model.dto.ShoppingDTO;
import br.com.bookstore.repository.OrderRepository;
import br.com.bookstore.service.exception.ObjectNotFoundException;

@Service
public class ShoppingService {

	private final OrderRepository orderRepository;

	@Autowired
	public ShoppingService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	/**
	 * Return all orders stored.
	 * @return
	 */
	public Iterable<Shopping> findAllShoppings() {
		return getRepository().findAll();
	}

	/**
	 * Insert a new shopping request.
	 * @param shopping
	 * @return
	 */
	public Shopping insertShopping(Date date, BasketDTO basketDTO, String status) {
		Shopping shopping = new Shopping(date, basketDTO.getCustomer(), status, basketDTO.getBooks());
		return getRepository().save(shopping);
	}
	
	/**
	 * Delete a shopping request by it's id.
	 * @param id
	 */
	public void deleteShoppingById(Integer id) {
		getRepository().deleteById(id);
	}

	/**
	 * Find a shopping request by it's id.
	 * @param id
	 * @return
	 */
	public Shopping find(Integer id) {
		Optional<Shopping> obj = getRepository().findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Kind: " + Shopping.class.getName()));
	}
	
	/**
	 * Update a shopping request.
	 * @param obj
	 * @return
	 */
	public Shopping update(ShoppingDTO obj) {
		Shopping shopping = find(obj.getId());
		updateShopping(shopping);		
		return getRepository().save(shopping);
	}

	private void updateShopping(Shopping shopping) {
		shopping.setCustomer(shopping.getCustomer());
		shopping.setStatus(shopping.getStatus());
		shopping.setBooks(shopping.getBooks());		
	}

	/**
	 * Find shopping requests by their status.
	 * @param status
	 * @return
	 */
	public List<Shopping> findShoppingsByStatus(Integer status) {
		return getRepository().findByStatus(status);
	}

	/**
	 * Return shopping status by it's id.
	 * @param id
	 * @return
	 */
	public String getStatusOfShopping(Integer id) {
		Shopping shopping = find(id);
		return shopping.getStatus();
	}
	
	public OrderRepository getRepository() {
		return orderRepository;
	}

}
