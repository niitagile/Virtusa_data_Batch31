package com.pharmacy.bean;

public class ParticularOrderProductBean {

	private int id;
	private int orderId;
	private String itemName;
	private int quantity;
	private float price;
	
	public ParticularOrderProductBean() {
		super();
	}
	public ParticularOrderProductBean(int orderId, String itemName, int quantity, float price) {
		super();
		this.orderId = orderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ParticularOrderProductBean [id=" + id + ", order_id=" + orderId + ", itemname=" + itemName
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
