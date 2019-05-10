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

import br.com.bookstore.model.dto.PaymentDTO;
import br.com.bookstore.service.PaymentService;

@RestController
@RequestMapping(value = "/v1/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Integer id) {
		PaymentDTO obj = paymentService.findPayment(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<PaymentDTO> insert(@RequestBody PaymentDTO paymentDTO) throws Exception {
		paymentDTO = paymentService.insertPayment(paymentDTO);
		return ResponseEntity.ok().body(paymentDTO);
	}

	@PutMapping(value = "/status")
	public PaymentDTO updatePaymentStatus(@PathVariable Integer id, @RequestBody PaymentDTO payment) throws Exception {
		return paymentService.updatePaymentStatus(payment);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletePayment(@PathVariable Integer id) {
		paymentService.deletePaymentById(id);
	}
	
}
