package com.example.demo.repo;

import java.util.Optional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Bank;
import com.example.demo.entity.Branch;

public interface BranchRepositoryCustom {

	public Optional<Branch> getBranchById(Long branchId);
}
