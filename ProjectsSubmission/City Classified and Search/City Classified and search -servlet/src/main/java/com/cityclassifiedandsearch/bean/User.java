package com.cityclassifiedandsearch.bean;

import java.util.Set;

public class User  {
	
	private int userId;
	private String userName;
	private String userEmail;
	private String password;
	private String mobile;
	private String userAddress;
	private String userCity;
	private String Role;
	private boolean enabled;
	private Set<Classified> classified;
	

	//Getters And Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	
	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<Classified> getClassified() {
		return classified;
	}

	public void setClassified(Set<Classified> classified) {
		this.classified = classified;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", password="
				+ password + ", mobile=" + mobile + ", userAddress=" + userAddress + ", userCity=" + userCity
				+ ", Role=" + Role + ", enabled=" + enabled + "]";
	}

	
		
}
