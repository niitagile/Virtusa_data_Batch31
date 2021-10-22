package com.springBoot.ConnectGlobe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyReportEntityRepo extends JpaRepository<MyReportEntity, Integer> {

	List<MyReportEntity> findByUserId(int uid);

}
