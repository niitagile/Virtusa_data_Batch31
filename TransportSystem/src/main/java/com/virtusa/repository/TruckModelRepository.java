package com.virtusa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.model.TruckModel;


public interface TruckModelRepository extends JpaRepository<TruckModel,Integer>{

	public List<TruckModel> findAllByModelName(String modelName);

	public boolean existsByModelName(String model);

	//public void deleteByTid(int tid);
	
	public TruckModel findByModelName(String modelName);

	public void deleteByModelName(String mtruck);

}
