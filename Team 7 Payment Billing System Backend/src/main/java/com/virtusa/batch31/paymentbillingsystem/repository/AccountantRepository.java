package com.virtusa.batch31.paymentbillingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.batch31.paymentbillingsystem.entities.Accountant;
import com.virtusa.batch31.paymentbillingsystem.entities.Branch;

public interface AccountantRepository extends JpaRepository<Accountant, Integer> {
	
	@Query("Select b from Branch b join b.accountants a where a.id = :id")
	public Branch getBranch(@Param("id") int id);
	
	@Query("Select a from Accountant a where a.username = :username AND password = :password")
	public Accountant isExists(@Param("username") String username, @Param("password") String password);

}
