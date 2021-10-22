package com.bank.beans;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;


@Entity
@Component
public class Account {
	
	@Id
	private int accno;
	private LocalDateTime opendate;
	private String accType;
	private String ifsc;
	private String upi;
	private float bal;

	@OneToOne(targetEntity=User.class, cascade = {CascadeType.ALL})  
	private User user;
	
	
	public User getUser() {
		return user;
	}



	public String getUpi() {
		return upi;
	}



	public void setUpi(String upi) {
		this.upi = upi;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Account(int accno, LocalDateTime opendate, String accType, String ifsc, float bal, User user, String upi) {
		super();
		this.accno = accno;
		this.opendate = opendate;
		this.accType = accType;
		this.ifsc = ifsc;
		this.bal = bal;
		this.user = user;
		this.upi= upi;
	}


	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public LocalDateTime getOpendate() {
		return opendate;
	}

	public void setOpendate(LocalDateTime opendate) {
		this.opendate = opendate;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public float getBal() {
		return bal;
	}

	public void setBal(float bal) {
		this.bal = bal;
	}
	
	
}
