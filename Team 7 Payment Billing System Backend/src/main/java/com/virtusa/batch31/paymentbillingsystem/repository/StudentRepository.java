package com.virtusa.batch31.paymentbillingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.batch31.paymentbillingsystem.entities.Course;
import com.virtusa.batch31.paymentbillingsystem.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("Select c from Course c join c.students s where s.rollNumber = :rollNumber")
	public Course getEnrolledCourse(@Param("rollNumber") int rollNumber);

	
}
