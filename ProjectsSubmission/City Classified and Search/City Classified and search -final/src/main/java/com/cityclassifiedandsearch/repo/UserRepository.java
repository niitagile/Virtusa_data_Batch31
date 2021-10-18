package com.cityclassifiedandsearch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cityclassifiedandsearch.bean.User;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserEmail(String userEmail);
	User findByUserId(int userId);
	User findByUserName(String userName);
	List<User> findAll();
}