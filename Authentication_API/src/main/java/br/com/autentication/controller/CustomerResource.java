package br.com.autentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.autentication.model.dto.AuthenticationDTO;
import br.com.autentication.model.dto.CustomerDTO;
import br.com.autentication.service.CustomerService;

@RestController
@RequestMapping(value = "/v1/customers")
public class CustomerResource {

	@Autowired
	private CustomerService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AuthenticationDTO> getCustomerAuthentication(@Validated @RequestBody CustomerDTO customerDTO) {
		AuthenticationDTO authenticationDTO = service.validadeCustomerCredentials(customerDTO); 
		return ResponseEntity.ok().body(authenticationDTO);
	}
	
}
