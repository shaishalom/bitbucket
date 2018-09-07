package com.example.demo.repo;

import java.util.Optional;

import com.example.demo.entity.Customer;

public interface CustomerRepositoryCustom {

	public Optional<Customer> getCustomerById(Long customerId);
}
