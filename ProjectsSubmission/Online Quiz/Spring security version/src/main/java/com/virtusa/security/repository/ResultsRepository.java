package com.virtusa.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.virtusa.security.model.Results;

public interface ResultsRepository extends JpaRepository<Results, Integer> {

	@Query("from Results where stdId=?1")
	List<Results> findResultsById(int stdId);
}
