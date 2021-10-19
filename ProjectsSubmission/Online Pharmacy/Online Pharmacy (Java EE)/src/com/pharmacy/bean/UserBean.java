package com.pharmacy.bean;

public class UserBean {
	private int id;
	private String name;
	private String username;
	private String email;
	private String phoneNumber;
	private String password;
	private String role;
	public UserBean() {}
	public UserBean(String name,String username, String email, String phoneNumber, String password, String role) {
		super();
		this.name=name;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", role=" + role + "]";
	}
	

}
