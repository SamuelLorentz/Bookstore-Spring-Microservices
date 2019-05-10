package br.com.autentication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.autentication.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
