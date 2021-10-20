package com.virtusa.batch31.paymentbillingsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.batch31.paymentbillingsystem.entities.Course;
import com.virtusa.batch31.paymentbillingsystem.services.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/")
	public Course createCourse(@RequestBody Course course) {
		return courseService.addCourse(course);
	}
	
	@GetMapping("/")
	public List<Course> getAllCourses(){
		return courseService.getAllCourses();
		
	}
	
	@GetMapping("/{id}")
	public Course getCourse(@PathVariable("id") int id) {
		return courseService.getCourse(id);
	}
	
	@PutMapping("/")
	public Course updateCourse(@RequestBody Course course) {
		return courseService.updateCourse(course);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCourse(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
		return "Deleted Successfully";
	}
}
