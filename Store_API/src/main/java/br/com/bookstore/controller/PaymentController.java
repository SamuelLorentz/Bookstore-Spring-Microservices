package br.com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bookstore.model.Payment;
import br.com.bookstore.model.dto.AuthenticationDTO;
import br.com.bookstore.model.dto.PaymentDTO;
import br.com.bookstore.service.PaymentService;

@RestController
@RequestMapping(value = "/v1/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
		Payment obj = paymentService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<AuthenticationDTO> insert(@RequestBody PaymentDTO paymentDTO) throws Exception {
		AuthenticationDTO authenticationDTO = paymentService.insertPayment(paymentDTO);
		return ResponseEntity.ok().body(authenticationDTO);
	}

	@PutMapping(value = "/{id}")
	public Payment updatePayment(@PathVariable Integer id, @RequestBody PaymentDTO payment) throws Exception {
		return paymentService.update(payment);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletePayment(@PathVariable Integer id) {
		paymentService.deletePaymentById(id);
	}
	
}
