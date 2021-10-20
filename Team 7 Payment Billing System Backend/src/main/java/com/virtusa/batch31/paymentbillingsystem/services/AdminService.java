package com.virtusa.batch31.paymentbillingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.batch31.paymentbillingsystem.entities.Admin;
import com.virtusa.batch31.paymentbillingsystem.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Admin isExists(String username, String password) {
		return adminRepository.isExists(username, password);
	}
	
	public Admin getAdmin(int id) {
		return adminRepository.findById(id).orElse(null);
	}
	
	public List<Admin> getAllAdmins(){
		return adminRepository.findAll();
	}
	
	public Admin updateAdmin(Admin admin) {
		Admin a = adminRepository.getById(admin.getId());
		a.setName(admin.getName());
		a.setUsername(admin.getUsername());
		a.setPassword(admin.getPassword());
		return adminRepository.save(a);
	}
	
	public void deleteAdmin(int id) {
		adminRepository.deleteById(id);
	}
}
