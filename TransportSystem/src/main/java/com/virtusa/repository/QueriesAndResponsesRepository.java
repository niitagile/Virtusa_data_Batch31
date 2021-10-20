package com.virtusa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.model.QueriesAndResponses;



public interface QueriesAndResponsesRepository extends JpaRepository<QueriesAndResponses,Integer> {
	
	public QueriesAndResponses findBySerialNumber(int serialNumber);

	//@Query(value = "SELECT u FROM QueriesAndResponses u WHERE u.responses IS NOT NULL AND u.username =?#{[0]}")
	public List<QueriesAndResponses> findAllByUserName(String userName);
	
	public List<QueriesAndResponses> findAllByFlag(boolean response);
}


 
