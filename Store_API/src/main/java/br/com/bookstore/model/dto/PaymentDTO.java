package br.com.bookstore.model.dto;

import java.io.Serializable;

import br.com.bookstore.model.Payment;

public class PaymentDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer requestId;
	private String status;
	private CreditCardDTO creditCardDTO;
	
	public PaymentDTO() {
		
	}
	
	public PaymentDTO(Payment obj) {
		this.id = obj.getId();
		this.requestId = obj.getShopping().getId();
		this.status = obj.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CreditCardDTO getCreditCardDTO() {
		return creditCardDTO;
	}

	public void setCreditCardDTO(CreditCardDTO creditCardDTO) {
		this.creditCardDTO = creditCardDTO;
	}
	
}
