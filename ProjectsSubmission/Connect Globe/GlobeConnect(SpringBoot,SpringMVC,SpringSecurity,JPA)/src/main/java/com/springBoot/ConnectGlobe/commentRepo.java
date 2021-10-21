package com.springBoot.ConnectGlobe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepo extends JpaRepository<commentEntity, Integer> {

	List<commentEntity> findBypId(int id);

	void deleteBypId(int id);

}
