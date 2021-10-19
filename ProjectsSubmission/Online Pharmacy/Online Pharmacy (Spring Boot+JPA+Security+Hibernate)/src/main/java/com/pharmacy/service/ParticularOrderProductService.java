package com.pharmacy.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.repo.OrdersRepo;
import com.pharmacy.repo.ParticularProdRepo;

@Service
public class ParticularOrderProductService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ParticularProdRepo productRepo;

	@Autowired
	private OrdersRepo ordersRepo;

	public ParticularOrderProductService(ParticularProdRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}

	public ParticularOrderBean addPartOrder(ParticularOrderBean prod) {
		return productRepo.save(prod);
	}

	public void deleteInTable(int id, boolean isMedicine) {
		if (!isMedicine) {
			this.productRepo.deleteByOrderId(id);
		}
		this.ordersRepo.deleteById(id);
	}

	public ParticularOrderBean updateByIdAndOrderId(ParticularOrderBean prod) {
		return this.productRepo.save(prod);
	}

	public List<ParticularOrderBean> getPartByOrderId(int order_id) {
		OrdersBean ord = this.ordersRepo.findById(order_id);
		return (this.productRepo).findByOrderBean(ord);
	}

	public List<ParticularOrderBean> getAllProds() {
		return this.productRepo.findAll();
	}

}
