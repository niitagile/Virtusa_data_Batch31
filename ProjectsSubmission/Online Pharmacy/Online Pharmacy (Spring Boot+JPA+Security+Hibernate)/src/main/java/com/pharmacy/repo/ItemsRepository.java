package com.pharmacy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.model.ItemsBean;

public interface ItemsRepository extends JpaRepository<ItemsBean, Integer> {

	ItemsBean findByDistributor(String distributor);
	
	List<ItemsBean> findByCategory(String category);
	
	boolean existsByDistributor(String distributor);
	
	int deleteByDistributor(String distributor);
	
	int getIdByDistributor(String distributor);
	
	ItemsBean findById(int id);
}
