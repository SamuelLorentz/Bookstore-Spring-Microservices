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
	private CreditCardRepository creditCardRepository;

	private static final String VALID = "Credentials sucessfuly validated.";
	private static final String INVALID = "Credentials failed to be validated.";

	public AuthenticationDTO validateCreditCardCredentials(CreditCardDTO creditCardDTO) {

		LocalDate dateOfDTO = LocalDate.parse(creditCardDTO.getDateExpire());
		CreditCard creditCard = findCard(creditCardDTO.getId());

		if (creditCardDTO.getFlag().equals(creditCard.getFlag())
				&& creditCardDTO.getToken().equals(creditCard.getToken())
				&& dateOfDTO.equals(creditCard.getLocalDateExpire())
				&& creditCardDTO.getNumber().equals(creditCard.getNumber())) {
			return new AuthenticationDTO(true, VALID);

		} else {
			return new AuthenticationDTO(false, INVALID);
		}

	}

	/**
	 * Find Credit Card By ID.
	 * 
	 * @param id
	 * @return
	 */
	public CreditCard findCard(Integer id) {
		Optional<CreditCard> obj = creditCardRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Kind: " + CreditCard.class.getName()));
	}

}
