package br.com.auditory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.auditory.model.dto.AuditoryDTO;
import br.com.auditory.model.dto.PaymentDTO;
import br.com.auditory.service.AuditoryService;

@RestController
@RequestMapping(value = "/v1/auditory")
public class AuditoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuditoryController.class);
	
	@Autowired
	private AuditoryService service;

	@PostMapping
	public ResponseEntity<AuditoryDTO> confirmPayment(@RequestBody PaymentDTO paymentDTO) {
		LOGGER.info("Payment Auditory: {}", paymentDTO);
		AuditoryDTO auditoryValidation = service.confirmPayment(paymentDTO);
		return ResponseEntity.ok().body(auditoryValidation);
	}

}
