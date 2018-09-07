package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Account;
import com.example.demo.repo.AccountRepository;
 
@RestController
 
public class AccountController {
 
	@Autowired
	private AccountRepository accountRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

 
    @GetMapping(value = "/accounts")
    @ResponseBody
    public List<Account> getAccounts() {
    	List<Account> list = accountRepository.findAll();
        return list;
    }
 
    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long accountId) throws AccountNotFoundException {
        
    	logger.info("AccountController->getAccount by id:{} has started",accountId);
    	Optional<Account> account = accountRepository.findById(accountId);
    	//this is gettting from db or cache (if exists)
    	//Optional<Account> account = accountRepository.getAccountById(accountId);

    	if (!account.isPresent())
    		throw new AccountNotFoundException("id-" + accountId);

    	return account.get();
    }
 
    @PostMapping(value = "/accounts")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
 
        logger.info("(Service Side) Creating Account: " + account.getAccountId());
 
        Account savedAccount = accountRepository.save(account);

    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAccount.getAccountId()).toUri();
    	logger.info("(Service Side) Add Account: " + account.getAccountId());

    	return ResponseEntity.created(location).build();
    }
 
    @PutMapping("/accounts/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable("id") long accountId) {
 
    	Optional<Account> accountOptional = accountRepository.findById(accountId);

    	if (!accountOptional.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	account.setAccountId(accountId);
    	
    	accountRepository.save(account);
    	logger.info("(Service Side) Editing Account: " + account.getAccountId());

    	return ResponseEntity.noContent().build();
 
    }
 
    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable("id") Long accountId) {
 
        logger.info("(Service Side) Deleting Account: " + accountId);
        accountRepository.deleteById(accountId);
    }
 
}

