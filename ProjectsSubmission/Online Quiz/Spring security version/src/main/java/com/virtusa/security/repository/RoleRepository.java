package com.virtusa.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
