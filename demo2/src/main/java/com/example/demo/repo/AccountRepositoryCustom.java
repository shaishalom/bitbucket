package com.example.demo.repo;

import java.util.Optional;

import com.example.demo.entity.Account;

public interface AccountRepositoryCustom {

	public Optional<Account> getAccountById(Long accountId) ;
}
