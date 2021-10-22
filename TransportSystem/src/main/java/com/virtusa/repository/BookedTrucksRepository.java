package com.virtusa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.model.BookedTrucks;



public interface BookedTrucksRepository extends JpaRepository<BookedTrucks,Integer> {

	
	List<BookedTrucks> findAllByBookedBy(String date);
	List<BookedTrucks> findAllByTruckModel(String date);
	public boolean existsByTruckNum(String truckNum);

	public BookedTrucks findByTruckNum(String truckNum);

}
