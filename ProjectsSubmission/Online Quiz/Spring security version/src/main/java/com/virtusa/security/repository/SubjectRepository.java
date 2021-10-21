package com.virtusa.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.virtusa.security.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{

	@Query("from Subject where sub_name=?1")
	Subject getByName(String subName);

}
