package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.beans.RaiseTicket;

@Repository
public interface RaiseQueryRepository extends JpaRepository<RaiseTicket, Integer> {

}
