package com.pharmacy.bean;

public class OrdersBean {

	private int orderId;
	private String username;
	private String distributorName;
	private String address;
	private float price;
	private String phoneNumber;
	private int totalQuantity;
	private String orderDate;
	private String status;
	
	public OrdersBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrdersBean [order_id=" + orderId + ", username=" + username + ", distributor_name=" + distributorName
				+ ", address=" + address + ", price=" + price + ", totalQuantity=" + totalQuantity + ", orderDate="
				+ orderDate + ", status=" + status + "]";
	}
	

}
