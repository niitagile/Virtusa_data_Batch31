package com.virtusa.batch31.paymentbillingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.batch31.paymentbillingsystem.entities.Branch;
import com.virtusa.batch31.paymentbillingsystem.entities.Student;
import com.virtusa.batch31.paymentbillingsystem.repository.BranchRepository;

@Service
public class BranchService {
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch addBranch(Branch branch) {
		return branchRepository.save(branch);
	}
	
	public Branch getBranch(int id) {
		return branchRepository.findById(id).orElse(null);
	}
	
	public List<Branch> getAllBranches(){
		return branchRepository.findAll();
	}
	
	public List<Student> getStudentsOfABranch(int id){
		return branchRepository.getStudentsOfABranch(id);
	}
	
	public Branch updateBranch(Branch branch) {
		Branch b = branchRepository.getById(branch.getId());
		b.setName(branch.getName());
		b.setAddress(branch.getAddress());
		return branchRepository.save(b);	
	}
	
	public void deleteBranch(int id) {
		branchRepository.deleteById(id);
	}
}
