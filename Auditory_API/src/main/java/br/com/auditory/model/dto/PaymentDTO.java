package br.com.auditory.model.dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer requestId;
	private String status;
	
	public PaymentDTO() {
		
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

}
