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
import com.virtusa.batch31.paymentbillingsystem.entities.Student;
import com.virtusa.batch31.paymentbillingsystem.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/{branchId}/{courseId}")
	public Student createStudent(@PathVariable("branchId") int branchId, @PathVariable("courseId") int courseId, @RequestBody Student student) {
		return studentService.createStudent(branchId, courseId, student);
	}
	
	@GetMapping("/")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{rollNo}")
	public Student getStudentByRollNo(@PathVariable("rollNo") int rollNo) {
		return studentService.getStudent(rollNo);
	}
	
	@GetMapping("/course/{rollNo}")
	public Course getEnrolledCourse(@PathVariable("rollNo") int rollNo) {
		return studentService.getEnrolledCourse(rollNo);
	}
	
	@GetMapping("/fee/{rollNo}")
	public int getRemainingFee(@PathVariable("rollNo") int rollNo) {
		return studentService.getRemainingFee(rollNo);
	}
	
	@PutMapping("/")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}
	
	@DeleteMapping("/{rollNo}")
	public String deleteStudent(@PathVariable("rollNo") int rollNo) {
		studentService.deleteStudent(rollNo);
		return "Deleted Successfully";
	}
}
