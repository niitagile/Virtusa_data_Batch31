package com.bank.controllers;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.beans.Account;
import com.bank.beans.User;
import com.bank.dao.UserDAO;


@Controller
@ComponentScan("com.bank.dao")
public class CommonController {
	

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/logoutsession")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/error")
	@ResponseBody
	public String error() {
//		System.out.println("=---------------------------error--------------------------");
		return "WELCOME BUDDY";
	}
	
	@GetMapping("/check")
	@ResponseBody
	public String check() {
//		System.out.println("=---------------------------check--------------------------");
		return "WELCOME CHECK";
	}

	@GetMapping("/loginsetsession")
	public String login(HttpServletRequest request) {
//		System.out.println("=---------------------------Mthode--------------------------");
		
		String username="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		} else {
		   username = principal.toString();
		}
		Optional<User> userlist= userDAO.findByEmail(username);
		if (userlist.isPresent()) {
			User user = userlist.get();
//			System.out.println(user.getName());
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", user.getId());
			session.setAttribute("role", user.getRole());
			if(user.getRole().equals("USER")) {
				return "redirect:/dashboard";
			}else {
				return "redirect:/showall";
			}
		}else {
			return "error";
		}
	}

	@PostMapping("/register")
	public String register(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("pin") String pin, @RequestParam("phone") String phone,
			@RequestParam("state") String state, @RequestParam("address") String address,
			@RequestParam("aadharproof") String aadharproof) {

		User user = new User(name, email, passwordEncoder.encode(password), pin, phone, "USER", state, address, "Not Approved", aadharproof,
				null);
		String ifsc="";
		switch (state) {
		case "Jharkhand":
			ifsc= "JHO5678";
			break;
		case "West Bengal":
			ifsc= "WBO5678";
			break;
		case "Orissa":
			ifsc= "ORO5678";
			break;
		case "Andhra Pradesh":
			ifsc= "APO5678";
			break;
		default:
			break;
		}
		Account account = new Account(10000000 + new Random().nextInt(90000000), LocalDateTime.now(), "SAVING", ifsc,
				0, user, "disable");
		user.setAccount(account);
		userDAO.addUserToDB(user, account);
		return "redirect:/";
	}

}
