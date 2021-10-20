package com.virtusa.batch31.paymentbillingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.virtusa.batch31.paymentbillingsystem.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	@Query("Select a from Admin a where a.username = :username AND password = :password")
	public Admin isExists(@Param("username") String username, @Param("password") String password);

}
