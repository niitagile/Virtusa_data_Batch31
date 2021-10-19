package com.pharmacy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;

public interface DistributorItemsRepo extends JpaRepository<DistributorItemBean, Integer> {

	int deleteByIdAndItemBean(int id, ItemsBean itemBean);

	List<DistributorItemBean> findByItemBean(ItemsBean itemBean);
	
	DistributorItemBean findById(int id);
	
	DistributorItemBean findDistributorItemBeanByItemBean(ItemsBean itemBean);
	
	@Query("DELETE FROM DistributorItemBean D WHERE D.id=?1")
	@Modifying
	void deleteDistributorItemBeanById(int id);
	
	@Query("SELECT price FROM DistributorItemBean WHERE id=?1")
	float getPriceById(int id);
	
	@Modifying
	@Query(value="DELETE FROM distributor_items WHERE items_id=:items_id",nativeQuery=true)
	void deleteByItemsId(@Param("items_id") int itemsId);
	
	DistributorItemBean findByItemBeanAndItemName(ItemsBean item,String itemName);
}
