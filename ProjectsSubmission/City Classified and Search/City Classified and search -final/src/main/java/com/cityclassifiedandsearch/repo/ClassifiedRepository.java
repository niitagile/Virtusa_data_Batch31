package com.cityclassifiedandsearch.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityclassifiedandsearch.bean.Classified;
import com.cityclassifiedandsearch.bean.User;

@Repository
public interface ClassifiedRepository extends JpaRepository<Classified, Integer> {
	List<Classified> findByUserId(int userId);
	List<Classified> findByApproval(boolean approval);
	List<Classified> findByClassifiedTitleContaining(String key);
	List<Classified> findByDescriptionContaining(String key);
	Classified findByClassifiedId(int classifiedId);
}
