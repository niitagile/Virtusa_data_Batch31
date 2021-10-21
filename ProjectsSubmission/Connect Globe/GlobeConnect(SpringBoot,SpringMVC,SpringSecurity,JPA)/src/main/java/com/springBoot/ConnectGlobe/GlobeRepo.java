package com.springBoot.ConnectGlobe;

import org.springframework.data.jpa.repository.JpaRepository;


public interface GlobeRepo extends JpaRepository<CredentialModel, Integer>{

	CredentialModel findByUsername(String username);

	
}
