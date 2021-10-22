package com.springBoot.ConnectGlobe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface suggestEntityRepo extends JpaRepository<suggestEntity, Integer> {

	List<suggestEntity> findByrId(int id);

	void deleteByrId(int id);

}
