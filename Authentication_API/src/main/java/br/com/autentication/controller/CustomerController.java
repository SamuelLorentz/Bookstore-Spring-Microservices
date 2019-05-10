package br.com.autentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.autentication.model.dto.AuthenticationDTO;
import br.com.autentication.model.dto.CustomerDTO;
import br.com.autentication.service.CustomerService;

@RestController
@RequestMapping(value = "/v1/customers")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<AuthenticationDTO> customerAuthentication(@Validated @RequestBody CustomerDTO customerDTO) {
		LOGGER.info("Customer Authentication: {}", customerDTO);
		AuthenticationDTO authenticationDTO = service.validadeCustomerCredentials(customerDTO); 
		return ResponseEntity.ok().body(authenticationDTO);
	}
	
}
