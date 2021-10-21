package com.jsp.classes;

public class SuggestionClass {
	private int userId;
	private String suggestion;
	private String name;
	public SuggestionClass(int userId, String suggestion, String name) {
		super();
		this.userId = userId;
		this.suggestion = suggestion;
		this.name = name;
	}
	public SuggestionClass() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SuggestionClass [userId=" + userId + ", suggestion=" + suggestion + ", name=" + name + "]";
	}
	
}
