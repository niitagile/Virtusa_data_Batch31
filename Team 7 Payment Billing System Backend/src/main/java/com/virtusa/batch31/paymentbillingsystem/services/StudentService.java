package com.virtusa.batch31.paymentbillingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.batch31.paymentbillingsystem.entities.Branch;
import com.virtusa.batch31.paymentbillingsystem.entities.Course;
import com.virtusa.batch31.paymentbillingsystem.entities.PaymentDetail;
import com.virtusa.batch31.paymentbillingsystem.entities.Student;
import com.virtusa.batch31.paymentbillingsystem.repository.BranchRepository;
import com.virtusa.batch31.paymentbillingsystem.repository.CourseRepository;
import com.virtusa.batch31.paymentbillingsystem.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public Student createStudent(int branchId, int courseId, Student student) {
		Student st = studentRepository.save(student);
		Branch branch = branchRepository.getById(branchId);
		Course course = courseRepository.getById(courseId);
		branch.getStudents().add(st);
		course.getStudents().add(st);
		branchRepository.save(branch);
		courseRepository.save(course);
		return st;
	}
	
	public Student getStudent(int rollNumber) {
		return studentRepository.findById(rollNumber).orElse(null);
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Course getEnrolledCourse(int rollNumber) {
		return studentRepository.getEnrolledCourse(rollNumber);
	}
	
	public int getRemainingFee(int rollNo) {
		Student s = getStudent(rollNo);
		List<PaymentDetail> p = s.getPaymentDetails();
		int paidFee = 0;
		for(int i=0; i<p.size(); i++) {
			int amount = Integer.parseInt(p.get(i).getAmount());
			paidFee+=amount;
		}
		Course c = getEnrolledCourse(rollNo);
		int courseFee = Integer.parseInt(c.getFee());
		return courseFee-paidFee;
	}
	
	public Student updateStudent(Student student) {
		Student st = studentRepository.getById(student.getRollNumber());
		st.setEmail(student.getEmail());
		st.setName(student.getName());
		st.setPhone(student.getPhone());
		return studentRepository.save(st);
	}
	
	public void deleteStudent(int rollNumber) {
		studentRepository.deleteById(rollNumber);
	}
}
