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
	private CustomerRepository customerRepository;

	private static final String VALID = "Credentials sucessfuly validated.";
	private static final String INVALID = "Credentials failed to be validated.";

	/**
	 * Authenticate Customer Credentials **Token**
	 * 
	 * @param customerDTO
	 * @return
	 */
	public AuthenticationDTO validadeCustomerCredentials(CustomerDTO customerDTO) {
		AuthenticationDTO authenticationDTO;
		Customer customer = findCustomer(customerDTO.getCustomer().getId());
		if (customerDTO.getPassword().equals(customer.getPassword())) {
			authenticationDTO = new AuthenticationDTO(true, VALID);
		} else {
			authenticationDTO = new AuthenticationDTO(false, INVALID);
		}
		return authenticationDTO;
	}

	/**
	 * Return Customer by ID.
	 * 
	 * @param id
	 * @return
	 */
	public Customer findCustomer(Integer id) {
		Optional<Customer> obj = customerRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Kind: " + Customer.class.getName()));
	}

}
