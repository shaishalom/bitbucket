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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BANK")
public class Bank {

	@Id	 
	@Column(name = "BANK_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long bankId;
	
		
	@OneToMany(cascade = CascadeType.ALL, 
	        orphanRemoval = true , mappedBy = "bank" , fetch=FetchType.LAZY)
	private List<Branch> branches = new ArrayList<>();
	

	public Bank() {
		super();
	}
	
	
	public Long getBankId() {
		return bankId;
	}


	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}


	public List<Branch> getBranches() {
		return branches;
	}


	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}





	
}
