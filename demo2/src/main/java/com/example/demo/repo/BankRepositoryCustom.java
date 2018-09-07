package com.example.demo.repo;

import java.util.Optional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Bank;

public interface BankRepositoryCustom {

	public Optional<Bank> getBankById(Long bankId);
}
