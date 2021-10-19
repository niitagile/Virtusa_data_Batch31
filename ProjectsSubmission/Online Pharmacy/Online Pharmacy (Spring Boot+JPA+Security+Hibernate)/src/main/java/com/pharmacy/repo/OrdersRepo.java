package com.pharmacy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharmacy.model.OrdersBean;

public interface OrdersRepo extends JpaRepository<OrdersBean, Integer> {

	public OrdersBean findById(int orderId);

	public OrdersBean findByUsernameAndOrderDateAndTotalQuantity(String username, String orderDate, int i);

	public List<OrdersBean> findByUsername(String username);

	public List<OrdersBean> findByDistributorName(String distributor);

	@Query(value = "select * from orders where order_date =:date", nativeQuery = true)
	public List<OrdersBean> findByOrderDate(@Param("date") String date);

	public List<OrdersBean> findByOrderDateAndDistributorName(String orderDate, String distributorName);

	@Query(value = "SELECT * FROM orders WHERE order_date=:order_date AND distributor_name=:distributor_name", nativeQuery = true)
	public List<OrdersBean> findByOrderDateAndDistributorName1(@Param("order_date") String orderDate,
			@Param("distributor_name") String distributorName);
}
