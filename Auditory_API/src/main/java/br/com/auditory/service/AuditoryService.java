package br.com.auditory.service;

import org.springframework.stereotype.Service;

import br.com.auditory.model.dto.AuditoryDTO;
import br.com.auditory.model.dto.PaymentDTO;

@Service
public class AuditoryService {

	public AuditoryDTO AuthorizeDelivery(PaymentDTO paymentDTO) {
			AuditoryDTO auditory;
		if (paymentDTO.getStatus().equals("PAYED")) {
			auditory = new AuditoryDTO(true, "Payment Confirmed");
		} else {
			auditory = new AuditoryDTO(false, "Payment Not Confirmed");
		}
		return auditory;
	}

}
