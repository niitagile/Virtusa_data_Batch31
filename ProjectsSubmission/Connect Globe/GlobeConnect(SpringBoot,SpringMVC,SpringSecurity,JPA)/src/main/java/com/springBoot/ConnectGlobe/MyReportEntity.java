package com.springBoot.ConnectGlobe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyReportEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rId;
	private int userId;
	private String issue;
	public MyReportEntity() {
		super();
	}
	public MyReportEntity(int userId, String issue) {
		super();
		
		this.userId = userId;
		this.issue = issue;
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
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	
	
}
