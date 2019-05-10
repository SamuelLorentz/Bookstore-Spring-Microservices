package br.com.auditory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.auditory.model.dto.AuditoryDTO;
import br.com.auditory.model.dto.PaymentDTO;
import br.com.auditory.service.AuditoryService;

@RestController
@RequestMapping(value = "/v1/auditory")
public class AuditoryResource {

	@Autowired
	private AuditoryService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AuditoryDTO> getCustomerAuthentication(@RequestBody PaymentDTO paymentDTO) {
		AuditoryDTO AuditoryValidation = service.AuthorizeDelivery(paymentDTO);
		return ResponseEntity.ok().body(AuditoryValidation);
	}

}
