package com.pharmacy.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "items")
@Transactional
public class ItemsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private int id;
	@Column(nullable = false)
	private String distributor;
	@Column(nullable = false)
	private String category;
	@OneToMany(mappedBy = "itemBean", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DistributorItemBean> items;

	public ItemsBean() {
		super();
	}

	public ItemsBean(String distributor, String category) {
		super();
		this.distributor = distributor;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setCategory(String category) {
		this.category = category;
	}

	public List<DistributorItemBean> getItems() {
		return items;
	}

	public void setItems(List<DistributorItemBean> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ItemsBean [id=" + id + ", distributor=" + distributor + ", category=" + category + "]";
	}

}
