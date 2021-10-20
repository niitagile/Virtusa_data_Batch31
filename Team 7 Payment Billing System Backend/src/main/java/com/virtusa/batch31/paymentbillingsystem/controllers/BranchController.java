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

import com.virtusa.batch31.paymentbillingsystem.entities.Branch;
import com.virtusa.batch31.paymentbillingsystem.entities.Student;
import com.virtusa.batch31.paymentbillingsystem.services.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@PostMapping("/")
	public Branch createBranch(@RequestBody Branch branch) {
		return branchService.addBranch(branch);
	}
	
	@GetMapping("/")
	public List<Branch> getAllBranches(){
		return branchService.getAllBranches();
	}
	
	@GetMapping("/{id}")
	public Branch getBranchById(@PathVariable("id") int id) {
		return branchService.getBranch(id);
	}
	
	@GetMapping("/students/{id}")
	public List<Student> getStudentsOfBranch(@PathVariable("id") int id){
		return branchService.getStudentsOfABranch(id);
	}
	
	@PutMapping("/")
	public Branch updateBranch(@RequestBody Branch branch) {
		return branchService.updateBranch(branch);
	}
	
	@DeleteMapping("/{id}")
	public String deleteBranch(@PathVariable("id") int id) {
		branchService.deleteBranch(id);
		return "Deleted Successfully";
	}
}
