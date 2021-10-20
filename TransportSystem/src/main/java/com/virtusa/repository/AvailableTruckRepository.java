package com.virtusa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.model.AvailableTrucks;

public interface AvailableTruckRepository extends JpaRepository<AvailableTrucks, Integer> {

	public AvailableTrucks findByTruckModel(String truckModel);
	public List<AvailableTrucks> findAllByTruckModel(String truckModel);
	public void deleteByTruckNum(String truckNum);
	public boolean existsByTruckModel(String truckModel);
	public boolean existsByTruckNum(String truckNum);

	public AvailableTrucks findByTruckNum(String truckNum);

}
