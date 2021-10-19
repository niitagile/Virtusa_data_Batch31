package com.pharmacy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "distributor_items")
public class DistributorItemBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private int id;
	@Column(nullable=false)
	private String itemName;
	@Column(nullable=false)
	private float price;
	private String description;
	private int quantity;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="items_id", referencedColumnName="id")
	private ItemsBean itemBean;
	public DistributorItemBean() {
		super();
	}
	public DistributorItemBean(String itemName, float price, String description, int quantity, ItemsBean itemBean) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.itemBean = itemBean;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public ItemsBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemsBean itemBean) {
		this.itemBean = itemBean;
	}
	@Override
	public String toString() {
		return "DistributorItemBean [id=" + id + ", itemName=" + itemName + ", price=" + price + ", description="
				+ description + ", quantity=" + quantity + ", itemBean=" + itemBean + "]";
	}
	
}
