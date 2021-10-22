package com.bank.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.beans.MyUserDetails;
import com.bank.beans.User;
import com.bank.dao.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user= userRepository.findByEmail(username);
		
		if (user.isPresent()) {
			return new MyUserDetails(user.get());
//			return user.map(MyUserDetails::new).get();
		}else {
			user.orElseThrow(() -> new UsernameNotFoundException("Not Found :" + username));
			return null;
		}
		
//		user.orElseThrow(()-> new UsernameNotFoundException("Not Found :"+ username));
//		System.out.println(user.get().getRole());
//		return user.map(MyUserDetails::new).get();
	}

}
