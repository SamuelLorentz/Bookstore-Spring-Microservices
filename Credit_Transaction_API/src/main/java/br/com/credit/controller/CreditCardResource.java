package br.com.credit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.credit.model.dto.AuthenticationDTO;
import br.com.credit.model.dto.CreditCardDTO;
import br.com.credit.service.CreditCardService;

@RestController
@RequestMapping(value = "/v1/credit/transactions")
public class CreditCardResource {

	@Autowired
	private CreditCardService service;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AuthenticationDTO> getCustomerAuthentication(@RequestBody CreditCardDTO creditCardDTO) {
		AuthenticationDTO authenticationDTO = service.validateCreditCardCredentials(creditCardDTO); 
		return ResponseEntity.ok().body(authenticationDTO);
	}

}
