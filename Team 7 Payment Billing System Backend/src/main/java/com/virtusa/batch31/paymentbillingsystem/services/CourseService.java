package com.virtusa.batch31.paymentbillingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.batch31.paymentbillingsystem.entities.Course;
import com.virtusa.batch31.paymentbillingsystem.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public Course getCourse(int id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	public Course updateCourse(Course course) {
		Course c = courseRepository.getById(course.getId());
		c.setName(course.getName());
		c.setDuration(course.getDuration());
		c.setFee(course.getFee());
		return courseRepository.save(c);
	}
	
	public void deleteCourse(int id) {
		courseRepository.deleteById(id);
	}
}
