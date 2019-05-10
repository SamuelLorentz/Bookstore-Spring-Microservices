package br.com.auditory.service;

import org.springframework.stereotype.Service;

import br.com.auditory.model.dto.AuditoryDTO;
import br.com.auditory.model.dto.PaymentDTO;

@Service
public class AuditoryService {

	/**
	 * 
	 * Verify if payment is confirmed
	 * 
	 * @param paymentDTO
	 * @return
	 */
	public AuditoryDTO confirmPayment(PaymentDTO paymentDTO) {
			AuditoryDTO auditory;
		if (paymentDTO.getStatus().equals("PAYED")) {
			auditory = new AuditoryDTO(true, "Payment Confirmed");
		} else {
			auditory = new AuditoryDTO(false, "Payment Not Confirmed");
		}
		return auditory;
	}

}
