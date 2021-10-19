package com.pharmacy.payload;


public class ItemPayload {
	private String itemName;
	private String description;
	private String category;
	private float price;
	private int quantity;
	private String distributor;
	public String getItemName() {
		return itemName;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getCategory() {
		return category;
	}

	public float getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemPayload [itemName=" + itemName + ", description=" + description + ", category=" + category
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
}
