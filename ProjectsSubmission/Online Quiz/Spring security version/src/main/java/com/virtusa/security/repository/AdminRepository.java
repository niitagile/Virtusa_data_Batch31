package com.virtusa.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.virtusa.security.model.Admin;
import com.virtusa.security.model.Expert;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	@Query("from Admin where admin_name=?1 and admin_password=?2")
	Admin findByNameAndPassword(String name,String password);
}