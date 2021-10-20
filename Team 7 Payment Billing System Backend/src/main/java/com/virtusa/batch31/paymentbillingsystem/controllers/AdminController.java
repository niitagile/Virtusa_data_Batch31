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

import com.virtusa.batch31.paymentbillingsystem.entities.Admin;
import com.virtusa.batch31.paymentbillingsystem.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping("/")
	public Admin createAdmin(@RequestBody Admin admin) {
		return service.createAdmin(admin);
	}

	@GetMapping("/{id}")
	public Admin getAdmin(@PathVariable("id") int id) {
		return service.getAdmin(id);
	}

	@GetMapping("/")
	public List<Admin> getAllAdmins() {
		return service.getAllAdmins();
	}

	@PutMapping("/")
	public Admin updateAdmin(@RequestBody Admin admin) {
		return service.updateAdmin(admin);
	}
	
	@DeleteMapping("/{id}")
	public String deleteAdmin(@PathVariable("id") int id) {
		service.deleteAdmin(id);
		return "Deleted Successfully";
	}
}
