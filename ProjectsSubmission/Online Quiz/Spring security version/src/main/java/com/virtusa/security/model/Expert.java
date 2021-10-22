package com.virtusa.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expert {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int expertId;
	private String expertName;
	private String expertPassword;
	
	public int getExpertId() {
		return expertId;
	}
	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public String getExpertPassword() {
		return expertPassword;
	}
	public void setExpertPassword(String expertPassword) {
		this.expertPassword = expertPassword;
	}
	
	
	
	
}
