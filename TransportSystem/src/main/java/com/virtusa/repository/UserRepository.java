package com.virtusa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	public User findByUserName(String userName);
	public List<User> findAll();
	public User findByUserNameAndRole(String userName,String role);
	public User findById(int id);
	public User findByEmail(String uname);
	public List<User> findAllByRole(String role );
	boolean existsByUserName(String userName);
	boolean existsByEmail(String email);
	boolean existsByUserNameAndRole(String userName,String role);
	
}
