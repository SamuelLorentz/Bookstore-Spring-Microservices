package br.com.bookstore.model.dto;

import java.io.Serializable;

public class AuditoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean validation;
	private String message;

	public AuditoryDTO() {

	}

	public AuditoryDTO(Boolean validation, String message) {
		this.validation = validation;
		this.message = message;
	}

	public Boolean getValidation() {
		return validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
