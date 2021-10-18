package com.cityclassifiedandsearch.bean;

public class Classified {
	
	private int classifiedId;
	private int userId;
	private String classifiedCategory;
	private String classifiedTitle;
	private String description;
	private User user;
	//Getters And Setters
	public int getClassifiedId() {
		return classifiedId;
	}

	public void setClassifiedId(int classifiedId) {
		this.classifiedId = classifiedId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getClassifiedCategory() {
		return classifiedCategory;
	}

	public void setClassifiedCategory(String classifiedCategory) {
		this.classifiedCategory = classifiedCategory;
	}

	public String getClassifiedTitle() {
		return classifiedTitle;
	}

	public void setClassifiedTitle(String classifiedTitle) {
		this.classifiedTitle = classifiedTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Classified [classifiedId=" + classifiedId + ", userId=" + userId + ", classifiedCategory="
				+ classifiedCategory + ", classifiedTitle=" + classifiedTitle + ", description=" + description
				+ "]";
	}
	
}
