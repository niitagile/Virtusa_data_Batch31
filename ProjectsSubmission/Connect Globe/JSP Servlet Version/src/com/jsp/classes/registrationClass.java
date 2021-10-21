package com.jsp.classes;

public class registrationClass {

	private int userId;
	private String fullname;
	private String email;
	private String mobileNumber;
	private String gender;
	private String roles;
	private String passsword;
	
	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public registrationClass() {
		super();
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

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "registrationClass [userId=" + userId + ", fullname=" + fullname + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", gender=" + gender + ", roles=" + roles + ", passsword=" + passsword + "]";
	}

	public registrationClass(int userId, String fullname, String email, String mobileNumber, String gender,
			String roles, String passsword) {
		super();
		this.userId = userId;
		this.fullname = fullname;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.roles = roles;
		this.passsword = passsword;
	}

	
	
	
	
}
