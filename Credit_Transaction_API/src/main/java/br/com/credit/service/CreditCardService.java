package br.com.credit.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.credit.model.CreditCard;
import br.com.credit.model.dto.AuthenticationDTO;
import br.com.credit.model.dto.CreditCardDTO;
import br.com.credit.repository.CreditCardRepository;
import br.com.credit.service.exception.ObjectNotFoundException;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository CreditCardRepository;

	private final static String Valid = "Credentials sucessfuly validated.";
	private final static String Invalid = "Credentials failed to be validated.";

	public AuthenticationDTO validateCreditCardCredentials(CreditCardDTO creditCardDTO) {
		AuthenticationDTO authenticationDTO;
		LocalDate dateOfDTO = LocalDate.parse(creditCardDTO.getDateExpire());
		CreditCard creditCard = find(creditCardDTO.getId());
		if (creditCardDTO.getFlag().equals(creditCard.getFlag()) && creditCardDTO.getToken() == creditCard.getToken()
				&& dateOfDTO.equals(creditCard.getLocalDateExpire())
				&& creditCardDTO.getNumber().equals(creditCard.getNumber())) {
			authenticationDTO = new AuthenticationDTO(true, Valid);

		} else {
			authenticationDTO = new AuthenticationDTO(false, Invalid);
		}

		return authenticationDTO;
	}

	public CreditCard find(Integer id) {
		Optional<CreditCard> obj = CreditCardRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Kind: " + CreditCard.class.getName()));
	}

}
