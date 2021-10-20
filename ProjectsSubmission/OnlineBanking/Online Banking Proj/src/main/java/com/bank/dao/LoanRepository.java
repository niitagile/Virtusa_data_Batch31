package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.beans.LoanQuery;

@Repository
public interface LoanRepository extends JpaRepository<LoanQuery, Integer> {

}
