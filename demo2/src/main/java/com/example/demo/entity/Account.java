package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@Column(name = "ACCOUNT_ID")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  accountId;
	
	@Column(name = "BALANCE")
	private Double balance;
	
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = Branch.class )
	@JoinColumn(name="BRANCH_ID", insertable = false, updatable = false)  
	private Branch branch;
	



	@OneToMany(cascade = CascadeType.ALL, 
			 orphanRemoval = true , mappedBy = "account" , fetch=FetchType.LAZY)
	private List<Customer> customers = new ArrayList<>();
	 

	public Account() {
		super();
	}



	public Account(Long accountId, Double balance, Branch branch) {
		this.accountId = accountId;
		this.balance = balance;
		this.branch = branch;
	}



	public Long getAccountId() {
		return accountId;
	}



	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}



	public Double getBalance() {
		return balance;
	}



	public void setBalance(Double balance) {
		this.balance = balance;
	}



//	public List<Customer> getCustomers() {
//		return customers;
//	}



	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}



	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	 public Long getBranch() {
		return branch.getBranchId();
	}

	
}
