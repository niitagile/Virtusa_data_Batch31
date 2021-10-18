package com.cityclassifiedandsearch.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", unique = true, nullable = false)
	private int userId;
	
	@Column(name = "username", unique = false, nullable = false, length = 20)
	private String userName;
	
	@Column(name = "useremail", unique = true, nullable = false, length = 30)
	private String userEmail;
	
	@Column(name = "password", unique = false, nullable = false, length = 60)
	private String password;
	
	@Column(name = "mobile", unique = false, nullable = true, length = 10)
	private String mobile;
	
	@Column(name = "useraddress", unique = false, nullable = true, length = 30)
	private String userAddress;
	
	@Column(name = "usercity", unique = false, nullable = true, length = 10)
	private String userCity;
	
	@Column(name = "enabled", unique = false, nullable = false)
	private char enabled;
	
	@Column(name = "role", unique = false, nullable = false, length = 5)
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private Collection<Classified> classifieds;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private Collection<CityDetails> cityDetails;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private Collection<Multimedia> carousel;

	public User() {
		super();
	}

	
	public User(int userId, String userName, String userEmail, String password, String mobile, String userAddress,
			String userCity, char enabled, String role, Collection<Classified> classifieds,
			Collection<CityDetails> cityDetails, Collection<Multimedia> carousel) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.mobile = mobile;
		this.userAddress = userAddress;
		this.userCity = userCity;
		this.enabled = enabled;
		this.role = role;
		this.classifieds = classifieds;
		this.cityDetails = cityDetails;
		this.carousel = carousel;
	}


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

	public char getEnabled() {
		return enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<Classified> getClassifieds() {
		return classifieds;
	}

	public void setClassifieds(Collection<Classified> classifieds) {
		this.classifieds = classifieds;
	}

	public Collection<CityDetails> getCityDetails() {
		return cityDetails;
	}

	public void setCityDetails(Collection<CityDetails> cityDetails) {
		this.cityDetails = cityDetails;
	}

	public Collection<Multimedia> getCarousel() {
		return carousel;
	}


	public void setCarousel(Collection<Multimedia> carousel) {
		this.carousel = carousel;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", password="
				+ password + ", mobile=" + mobile + ", userAddress=" + userAddress + ", userCity=" + userCity
				+ ", enabled=" + enabled + ", role=" + role + ", classifieds=" + classifieds + ", cityDetails="
				+ cityDetails + ", carousel=" + carousel + "]";
	}
	
	
}
