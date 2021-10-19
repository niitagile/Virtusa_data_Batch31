package com.pharmacy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pharmacy.model.UserBean;
import com.pharmacy.payload.LoginBean;
import com.pharmacy.security.JwtUtil;
import com.pharmacy.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ModelAndView authenticateUser(@ModelAttribute("user") LoginBean user, HttpServletRequest request,HttpServletResponse response) throws IOException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		HttpSession session=request.getSession();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateJwtToken(authentication);
		session.setAttribute("username", user.getUsername());
		session.setAttribute("Authorization", "Bearer "+jwt);
		try {
			session.setAttribute("token", userService.decodeToken(jwt));
		} catch (UnsupportedEncodingException e) {
			return new ModelAndView("error");
		}

		return new ModelAndView("dashboard");
	}
	
	@GetMapping("/deleteusers")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView deleteUser(@RequestParam("userId") int id,Model model) {
		userService.deleteByUserId(id);
		model.addAttribute("message", "Deleted Successfully");
		return new ModelAndView("dashboard");
	}
	
	
	@PostMapping("/register")
	public ModelAndView addUser(@ModelAttribute("user") UserBean user, Model model) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("ROLE_"+user.getRole());
		userService.addUser(user);
		model.addAttribute("user", new UserBean());
		model.addAttribute("msg", "Registered Successfully");
		return new ModelAndView("signup");
	}

	@PostMapping("/updateprofile")
	@PreAuthorize("hasAnyRole('ADMIN','USER','DISTRIBUTOR')")
	public ModelAndView update(@ModelAttribute("user") UserBean user,Model model,HttpServletRequest request) {
		String token=(String) request.getSession().getAttribute("token");
		if(token.contains("USER")) {
			user.setRole("ROLE_USER");
		}
		else if(token.contains("DISTRIBUTOR")) {
			user.setRole("ROLE_DISTRIBUTOR");
		}
		if(user.getPassword().length()<50) {
		user.setPassword(encoder.encode(user.getPassword()));}
		userService.addUser(user);
		model.addAttribute("message","SUCCESS");
		return new ModelAndView("dashboard");
	}

	@GetMapping("/deleteuser")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView delete(@RequestParam int userId, Model model) {
		userService.deleteByUserId(userId);
		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("dashboard");
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserBean>> getAllUsers() {
		List<UserBean> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/find/{username}")
	public ResponseEntity<UserBean> findByUser(@PathVariable("username") String username) {
		UserBean user = userService.getUser(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/find/role/{username}")
	public ResponseEntity<Object> findByUserRole(@PathVariable("username") String username) {
		Object user = userService.getRole(username);
		if (user == null) {
			return new ResponseEntity<>("Not Found", HttpStatus.OK);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}


	@PutMapping("/update/{id}")
	public ResponseEntity<UserBean> updateUser(@RequestBody UserBean user, @PathVariable("id") int id) {
		UserBean updateUser = userService.updateUser(user, id);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@PutMapping("/update/{username}/{password}")
	public ResponseEntity<UserBean> updateUser(@RequestBody UserBean user, @PathVariable("username") String username,
			@PathVariable("password") String password) {

		UserBean updateUser = userService.updateUser(user, username, password);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{username}/{password}")
	public String deleteByUsnameAndPassword(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		userService.deleteByUsernameAndPass(username, password);

		return "";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") int id) {
		userService.deleteByUserId(id);

		return "";
	}
}
