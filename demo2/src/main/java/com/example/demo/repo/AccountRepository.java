package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Bank;
import com.example.demo.entity.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom{
	
}

