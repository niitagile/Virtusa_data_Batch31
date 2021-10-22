package com.springBoot.ConnectGlobe;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserModel, Integer> {

	UserModel findByEmail(String username);


	
}
