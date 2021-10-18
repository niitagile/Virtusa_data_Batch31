package com.cityclassifiedandsearch.bean;

public class Citydetails {
	
	private int cityId;
	private int userId;
	private String category;	
	private String name;	
	private String address;	
	private String city;	
	private String link;

	//Getters And Setters
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Citydetails [cityId=" + cityId + ", userId=" + userId + ", category=" + category + ", name=" + name
				+ ", address=" + address + ", city=" + city + ", link=" + link + "]";
	}
}
