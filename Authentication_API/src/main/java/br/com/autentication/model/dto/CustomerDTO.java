package br.com.autentication.model.dto;

import java.io.Serializable;

import br.com.autentication.model.Customer;

public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Customer customer;
	private String password;
	
	public CustomerDTO() {

	}

	public CustomerDTO(Customer obj, String password) {
		this.customer = obj;
		this.password = password;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
