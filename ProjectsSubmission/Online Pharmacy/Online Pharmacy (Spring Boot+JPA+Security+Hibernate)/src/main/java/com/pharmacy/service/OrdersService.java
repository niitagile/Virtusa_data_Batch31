package com.pharmacy.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.repo.OrdersRepo;
import com.pharmacy.repo.ParticularProdRepo;

@Service
public class OrdersService {

	@Autowired
	private ParticularProdRepo productRepo;

	@Autowired
	private OrdersRepo ordersRepo;

	public OrdersService(ParticularProdRepo productRepo, OrdersRepo ordersRepo) {
		super();
		this.productRepo = productRepo;
		this.ordersRepo = ordersRepo;
	}

	public int getOrderId(OrdersBean order) {
		OrdersBean ord = this.ordersRepo.findByUsernameAndOrderDateAndTotalQuantity(order.getUsername(),
				order.getOrderDate(), order.getTotalQuantity());

		return ord.getOrderId();
	}

	public String addOrder(OrdersBean order, List<ParticularOrderBean> prodBean) {
		OrdersBean orderBean = this.ordersRepo.save(order);
		for (ParticularOrderBean prod : prodBean) {
			prod.setOrderBean(orderBean);
			this.productRepo.save(prod);
		}
		if (orderBean != null)
			return "SUCCESS";
		else
			return "not inserted";
	}

	public String addFile(OrdersBean order) {
		OrdersBean orderBean = this.ordersRepo.save(order);

		if (orderBean != null)
			return "SUCCESS";
		else
			return "not inserted";
	}

	public List<OrdersBean> getOrdersByDate(String date, String role, String username) {
		List<OrdersBean> orders=new ArrayList<>();
		if (role.equals("ADMIN")) {
			orders = this.ordersRepo.findByOrderDate(date);
		}
		else if(role.equals("DISTRIBUTOR")) {
			orders = this.ordersRepo.findByOrderDateAndDistributorName(date, username);
			
		}
		return orders;
	}

	public List<OrdersBean> getOrdersByDistributor(String distributorName) {
		return ordersRepo.findByDistributorName(distributorName);
	}

	public List<OrdersBean> getAllOrders() {
		return this.ordersRepo.findAll();
	}

	public List<OrdersBean> getAllOrdersByUsername(String username) {
		return this.ordersRepo.findByUsername(username);
	}

	@Transactional
	public void deleteOrderById(int orderId) {
		this.ordersRepo.deleteById(orderId);
	}

	@Transactional
	public OrdersBean updateOrderStatus(int orderId, String status, String message) {
		OrdersBean ord = this.ordersRepo.findById(orderId);
		ord.setStatus(status);
		ord.setMessage(message);
		return this.ordersRepo.save(ord);

	}

	public List<OrdersBean> getOrdersByNameAndRole(String name, String role) {
		
		if (role.equals("USER")) {
			return this.ordersRepo.findByUsername(name);

		} else {
			return this.ordersRepo.findByDistributorName(name);
		}
	}

	public OrdersBean getOrderByOrdeId(int orderId) {
		return this.ordersRepo.findById(orderId);
	}

	public void updateOrder(OrdersBean newOrder) {
		OrdersBean order = ordersRepo.findById(newOrder.getOrderId());
		order.setStatus(newOrder.getStatus());
		order.setMessage(newOrder.getMessage());
		order.setDistributorName(newOrder.getDistributorName());
		order.setTotalQuantity(newOrder.getTotalQuantity());
		order.setPrice(newOrder.getPrice());
		ordersRepo.save(order);
	}
}
