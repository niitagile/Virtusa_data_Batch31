package com.virtusa.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.security.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
