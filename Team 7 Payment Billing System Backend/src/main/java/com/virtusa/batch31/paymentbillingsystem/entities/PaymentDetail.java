package com.virtusa.batch31.paymentbillingsystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
		
	private String amount;
	private String date; //yyyy-mm-dd
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
}
