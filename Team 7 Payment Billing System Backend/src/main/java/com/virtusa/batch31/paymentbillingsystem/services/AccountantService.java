package com.virtusa.batch31.paymentbillingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.batch31.paymentbillingsystem.entities.Accountant;
import com.virtusa.batch31.paymentbillingsystem.entities.Branch;
import com.virtusa.batch31.paymentbillingsystem.repository.AccountantRepository;
import com.virtusa.batch31.paymentbillingsystem.repository.BranchRepository;

@Service
public class AccountantService {
	@Autowired
	private AccountantRepository accountantRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	public Accountant createAccountant(int branchId, Accountant accountant) {
		Accountant acc = accountantRepository.save(accountant);
		Branch branch = branchRepository.getById(branchId);
		branch.getAccountants().add(acc);
		branchRepository.save(branch);
		return acc; 
	}
	
	public Accountant isExists(String username, String password) {
		return accountantRepository.isExists(username, password);
	}
	
	public Accountant getAccountant(int id) {
		return accountantRepository.findById(id).orElse(null); 	
	}
	
	public List<Accountant> getAllAccountants(){
		return accountantRepository.findAll();
	}
	
	public Branch getBranch(int accId) {
		return accountantRepository.getBranch(accId);
	}
	
	public Accountant updateAccountant(Accountant accountant) {
		Accountant acc = accountantRepository.getById(accountant.getId());
		if(accountant.getClass()!=null) {
			acc.setName(accountant.getName());
		}
		if(accountant.getSalary()!=null) {
			acc.setSalary(accountant.getSalary());	
		}
		if(accountant.getUsername()!=null) {
			acc.setUsername(accountant.getUsername());	
		}
		if(accountant.getPassword()!=null) {
			acc.setPassword(accountant.getPassword());	
		}
		return accountantRepository.save(acc);		
	}
	
	public void deleteAccountant(int id) {
		accountantRepository.deleteById(id);
	}
}
