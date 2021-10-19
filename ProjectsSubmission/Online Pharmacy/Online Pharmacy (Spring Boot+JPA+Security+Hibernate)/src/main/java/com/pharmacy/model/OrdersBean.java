package com.pharmacy.model;

import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "orders")
public class OrdersBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private int orderId;

	@Column(nullable = false)
	private String username;

	@Column(nullable = true)
	private String distributorName;
	private String address;
	private float price;

	private String phoneNumber;
	private int totalQuantity;
	@Column(nullable = false)
	private String orderDate;
	private String status;
	@OneToMany(mappedBy = "orderBean", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ParticularOrderBean> partProds;
	@Lob
	@Column(name = "prescription")
	private byte[] prescription;
	private boolean medicine;
	@Column(length = 50)
	private String message;
	@Transient
	private String base64Image;

	public String getBase64Image() {
		base64Image = Base64.getEncoder().encodeToString(this.prescription);
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public OrdersBean(int orderId, String username, String distributorName, String address, float price,
			String phoneNumber, int totalQuantity, String orderDate, String status, List<ParticularOrderBean> partProds,
			byte[] prescription) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.distributorName = distributorName;
		this.address = address;
		this.price = price;
		this.phoneNumber = phoneNumber;
		this.totalQuantity = totalQuantity;
		this.orderDate = orderDate;
		this.status = status;
		this.partProds = partProds;
		this.prescription = prescription;
	}

	public OrdersBean() {
		super();
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public List<ParticularOrderBean> getPartProds() {
		return partProds;
	}

	public void setPartProds(List<ParticularOrderBean> partProds) {
		this.partProds = partProds;
	}

	public byte[] getPrescription() {
		return prescription;
	}

	public void setPrescription(byte[] prescription) {
		this.prescription = prescription;
	}

	public boolean isMedicine() {
		return medicine;
	}

	public String getMessage() {
		return message;
	}

	public void setMedicine(boolean medicine) {
		this.medicine = medicine;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
