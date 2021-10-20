package com.virtusa.batch31.paymentbillingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.batch31.paymentbillingsystem.entities.Branch;
import com.virtusa.batch31.paymentbillingsystem.entities.Student;

public interface BranchRepository extends JpaRepository<Branch, Integer>{
	
	@Query("Select b.students from Branch b where b.id = :branchId")
	public List<Student> getStudentsOfABranch(@Param("branchId") int branchId);
}
