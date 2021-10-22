package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.beans.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{

	@Query("from Subject where sub_name=?1")
	Subject getByName(String subName);

}
