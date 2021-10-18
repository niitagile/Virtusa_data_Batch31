package com.cityclassifiedandsearch.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityclassifiedandsearch.bean.CityDetails;

@Repository
public interface CityDetailsRepository extends JpaRepository<CityDetails, Integer> {
	List<CityDetails> findByUserId(int userId);
	List<CityDetails> findByCityContaining(String key);
	List<CityDetails> findByNameContaining(String key);
	List<CityDetails> findByAddressContaining(String key);
	List<CityDetails> findByCategoryContaining(String key);
}
