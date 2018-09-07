package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.dto.GenderEnum;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long customerId;
	
	@ManyToOne (targetEntity = Account.class , fetch = FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_ID", insertable = false, updatable = false) 
	private Account account;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "GENDER")
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;

	public Customer() {
		super();
	}
	
	public Customer(Long customerId, String firstName, String lastName, GenderEnum gender) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
}
