package com.springBoot.ConnectGlobe;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface uploadEntityRepo extends JpaRepository<uploadEntity, Integer>{

	List<uploadEntity> findByUserId(int id);



	
}
