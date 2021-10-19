package com.pharmacy.bean;

public class DistributorItemBean {
	private int id;
	private int itemsId;
	private String itemName;
	private float price;
	private String description;
	private int quantity;
	public DistributorItemBean() {
	}
	
	public DistributorItemBean(int id, int itemsId, String itemName, float price, String description, int quantity) {
		super();
		this.id = id;
		this.itemsId = itemsId;
		this.itemName = itemName;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemsId() {
		return itemsId;
	}
	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "DistributorItemBean [id=" + id + ", itemsId=" + itemsId + ", itemName=" + itemName + ", price=" + price
				+ ", description=" + description + ", quantity=" + quantity + "]";
	}
	

}
