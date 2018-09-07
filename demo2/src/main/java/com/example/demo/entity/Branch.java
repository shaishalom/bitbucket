package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BRANCH")
public class Branch {

	@Id
	@Column(name = "branchId")
	//@GeneratedValue
	private Long branchId;

	@Column(name = "address")
	private String address;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "branch", fetch=FetchType.LAZY)
	private List<Account> accounts = new ArrayList<>();


	
	public Branch(Long branchId, String address, Bank bank) {
		super();
		this.branchId = branchId;
		this.address = address;
		this.bank = bank;
	}

	@ManyToOne(targetEntity = Bank.class, fetch = FetchType.LAZY)
	@JoinColumn(name="BANK_ID", insertable = false, updatable = false)  
	private Bank bank;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Long getBank() {
		return bank.getBankId();
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Branch() {
		super();
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
