package com.jsp.classes;

public class MyReport {

	private int rId;
	private int userId;
	private String issue;
	private String name;
	public MyReport(int rId, int userId, String issue,String name) {
		super();
		this.rId = rId;
		this.userId = userId;
		this.issue = issue;
		this.name=name;
	}
	public MyReport() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
