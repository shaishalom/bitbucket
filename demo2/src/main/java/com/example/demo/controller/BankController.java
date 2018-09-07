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
import com.example.demo.entity.Bank;
import com.example.demo.repo.BankRepository;
 
@RestController
 
public class BankController {
 
	
   private static final Logger logger = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private BankRepository bankRepository;
	
 
 
    @GetMapping(value = "/banks")
    @ResponseBody
    public List<Bank> getBanks() {
    	List<Bank> list = bankRepository.findAll();
        return list;
    }
 
    @GetMapping("/banks/{id}")
    public Bank getBank(@PathVariable("id") Long bankId) throws BankNotFoundException {
        
       	logger.info("BankController->getBank by id:{} has started",bankId);

    //	Optional<Bank> bank = bankRepository.findById(bankId);
    	Optional<Bank> bank = bankRepository.getBankById(bankId);

    	if (!bank.isPresent())
    		throw new BankNotFoundException("id-" + bankId);

    	return bank.get();
    }
 
    @PostMapping(value = "/banks")
    public ResponseEntity<Object> createBank(@RequestBody Bank bank) {
 
        logger.info("(Service Side) Creating Bank: " + bank.getBankId());
 
        Bank savedBank = bankRepository.save(bank);

    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBank.getBankId()).toUri();
    	logger.info("(Service Side) Add Bank: " + bank.getBankId());

    	return ResponseEntity.created(location).build();
    }
 
    @PutMapping("/banks/{id}")
    public ResponseEntity<Object> updateBank(@RequestBody Bank bank, @PathVariable("id") long bankId) {
 
    	Optional<Bank> bankOptional = bankRepository.findById(bankId);

    	if (!bankOptional.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	bank.setBankId(bankId);
    	
    	bankRepository.save(bank);
    	logger.info("(Service Side) Editing Bank: " + bank.getBankId());

    	return ResponseEntity.noContent().build();
 
    }
 
    @DeleteMapping("/banks/{id}")
    public void deleteBank(@PathVariable("id") Long bankId) {
 
        logger.info("(Service Side) Deleting Bank: " + bankId);
        bankRepository.deleteById(bankId);
    }
 
}

