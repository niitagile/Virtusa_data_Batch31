package com.springBoot.ConnectGlobe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class suggestEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sId;
	private int rId;
	private int userId;
	private String suggest;
	public suggestEntity(int rId, int userId, String suggest) {
		super();
		this.rId = rId;
		this.userId = userId;
		this.suggest = suggest;
	}
	public suggestEntity() {
		super();
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public int getsId() {
		return sId;
	}
	
}
