package com.bank.beans;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class RaiseTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String query;
	private String status;
	private LocalDateTime dateTime;
	
	@OneToOne(targetEntity = User.class, cascade = {CascadeType.ALL})
	private User user;
	

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public RaiseTicket() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RaiseTicket( String query, String status, LocalDateTime dateTime, User user) {
		super();
		this.query = query;
		this.status = status;
		this.dateTime = dateTime;
		this.user= user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
