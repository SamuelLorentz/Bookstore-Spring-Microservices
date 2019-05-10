package br.com.credit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.credit.model.dto.AuthenticationDTO;
import br.com.credit.model.dto.CreditCardDTO;
import br.com.credit.service.CreditCardService;

@RestController
@RequestMapping(value = "/v1/credit/transactions")
public class CreditCardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardController.class);
	
	@Autowired
	private CreditCardService service;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AuthenticationDTO> validateCreditCardCredentials(@RequestBody CreditCardDTO creditCardDTO) {
		LOGGER.info("validate Credit Card Credentials: {}", creditCardDTO);
		AuthenticationDTO authenticationDTO = service.validateCreditCardCredentials(creditCardDTO); 
		return ResponseEntity.ok().body(authenticationDTO);
	}

}
