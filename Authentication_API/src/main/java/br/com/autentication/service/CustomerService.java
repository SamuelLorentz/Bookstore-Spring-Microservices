package br.com.autentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.autentication.model.Customer;
import br.com.autentication.model.dto.AuthenticationDTO;
import br.com.autentication.model.dto.CustomerDTO;
import br.com.autentication.repository.CustomerRepository;
import br.com.autentication.service.exception.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository CustomerRepository;

	private final static String Valid = "Credentials sucessfuly validated.";
	private final static String Invalid = "Credentials failed to be validated.";

	public AuthenticationDTO validadeCustomerCredentials(CustomerDTO customerDTO) {
		AuthenticationDTO authenticationDTO;
		Customer customer = find(customerDTO.getCustomer().getId());
		if (customerDTO.getPassword().equals(customer.getPassword())) {
			authenticationDTO = new AuthenticationDTO(true, Valid);
		} else {
			authenticationDTO = new AuthenticationDTO(false, Invalid);
		}
		return authenticationDTO;
	}

	public Customer find(Integer id) {
		Optional<Customer> obj = CustomerRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Kind: " + Customer.class.getName()));
	}

}
