package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.beans.Admin;
import com.example.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	@Query("from Student where std_name=?1 and std_password=?2")
	Student findByNameAndPassword(String name,String password);
	
}
