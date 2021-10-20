package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beans.Results;

public interface ResultsRepository extends JpaRepository<Results, Integer> {

}
