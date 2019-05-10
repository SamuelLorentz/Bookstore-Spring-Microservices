package br.com.credit.model.dto;

import java.io.Serializable;

public class CreditCardDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String flag;
	private Integer token;
	private String dateExpire;
	private Integer number;
	
	public CreditCardDTO() {
		
	}
	
	public CreditCardDTO(Integer id, String flag, Integer token, String dateExpire, Integer number) {
		super();
		this.id = id;
		this.flag = flag;
		this.token = token;
		this.dateExpire = dateExpire;
		this.number = number;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public Integer getToken() {
		return token;
	}
	
	public void setToken(Integer token) {
		this.token = token;
	}
	
	public String getDateExpire() {
		return dateExpire;
	}
	
	public void setDateExpire(String dateExpire) {
		this.dateExpire = dateExpire;
	}

}
