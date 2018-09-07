package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>, BranchRepositoryCustom{
	
}


