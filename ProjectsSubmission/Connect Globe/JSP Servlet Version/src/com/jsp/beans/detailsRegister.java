package com.jsp.beans;

public class detailsRegister {
	private int uId;
	private String fullname;
	private String email;
	private String mobileNumber;
	private String gen;
	private String role;
	
	public detailsRegister() {
		super();
	}
	public detailsRegister(int uId, String fullname, String email, String mobileNumber, String gen, String role) {
		super();
		this.uId = uId;
		this.fullname = fullname;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.gen = gen;
		this.role = role;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "detailsRegister [uId=" + uId + ", fullname=" + fullname + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", gen=" + gen + ", role=" + role + "]";
	}
	

}


