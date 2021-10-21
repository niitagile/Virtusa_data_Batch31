package com.jsp.classes;

public class commnetModel {
	private String userName;
	private String reply;
	public commnetModel(String userName, String reply) {
		super();
		this.userName = userName;
		this.reply = reply;
	}
	public commnetModel() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	

}
