package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.beans.Admin;
import com.example.beans.Expert;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	@Query("from Admin where admin_name=?1 and admin_password=?2")
	Admin findByNameAndPassword(String name,String password);
}
