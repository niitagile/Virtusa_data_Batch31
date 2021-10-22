package com.bank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	public Controller() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/home")
	public String index() {
		return "WELCOME TO HOME PAGE";
	}
	@GetMapping("/admin")
	public String admin() {
		return "WELCOME TO ADMIN PAGE";
	}
	@GetMapping("/user")
	public String user() {
		return "WELCOME TO USER PAGE";
	}
	
	

}
