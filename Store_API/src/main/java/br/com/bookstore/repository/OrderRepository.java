package br.com.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bookstore.model.Shopping;

@Repository
public interface OrderRepository extends CrudRepository<Shopping, Integer> {

	public List<Shopping> findByStatus(Integer status);
}
