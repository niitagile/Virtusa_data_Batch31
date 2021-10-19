package com.pharmacy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;

public interface ParticularProdRepo extends JpaRepository<ParticularOrderBean, Integer> {
	
	List<ParticularOrderBean> findByOrderBean(OrdersBean order);
	
	@Modifying
	@Query(value="DELETE FROM particular_order WHERE order_id=:order_id",nativeQuery=true)
	void deleteByOrderId(@Param("order_id") int orderId);
	

}
