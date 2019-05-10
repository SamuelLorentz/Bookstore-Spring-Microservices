package br.com.credit.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer token;
	private Integer number;
	private String flag;
	private LocalDate dateExpire;
	
	public CreditCard() {
		
	}
	
	public CreditCard(String flag, Integer token, LocalDate dateExpire, Integer number) {
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
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public LocalDate getLocalDateExpire() {
		return dateExpire;
	}
	
	public void setLocalDateExpire(LocalDate dateExpire) {
		this.dateExpire = dateExpire;
	}
	
}
