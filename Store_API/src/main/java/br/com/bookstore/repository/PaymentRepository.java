package br.com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bookstore.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
