package br.com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bookstore.model.Basket;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {

}
