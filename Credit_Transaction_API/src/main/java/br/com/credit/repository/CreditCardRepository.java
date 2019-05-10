package br.com.credit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.credit.model.CreditCard;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Integer> {

}
