
package com.bank.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;

@Entity
@Component
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String password;
	private String pin;
	private String phone;
	private String role;
	private String state;
	private String address;
	private String status;
	private String aadharproof;
	
	@OneToOne(targetEntity = Account.class, cascade = {CascadeType.ALL})
	private Account account;
	
	

	public User(String name, String email, String password, String pin, String phone, String role, String state,
			String address, String status, String aadharproof, Account account) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.pin = pin;
		this.phone = phone;
		this.role = role;
		this.state = state;
		this.address = address;
		this.status = status;
		this.aadharproof = aadharproof;
		this.account = account;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAadharproof() {
		return aadharproof;
	}

	public void setAadharproof(String aadharproof) {
		this.aadharproof = aadharproof;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


}
