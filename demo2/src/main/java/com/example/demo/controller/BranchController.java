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

import com.example.demo.entity.Branch;
import com.example.demo.repo.BranchRepository;
 
@RestController
 
public class BranchController {
 
   private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	private BranchRepository branchRepository;
	
 
 
    @GetMapping(value = "/branches")
    @ResponseBody
    public List<Branch> getBranches() {
    	List<Branch> list = branchRepository.findAll();
        return list;
    }
 
    @GetMapping("/branches/{id}")
    public Branch getBranch(@PathVariable("id") Long branchId) throws BranchNotFoundException {

    	logger.info("BranchController->getBranch by id:{} has started",branchId);
    	
    	//find by cache
       	//Optional<Branch> branch = branchRepository.getBranchById(branchId);

    	Optional<Branch> branch = branchRepository.findById(branchId);

    	if (!branch.isPresent())
    		throw new BranchNotFoundException("id-" + branchId);

    	return branch.get();
    }
 
    @PostMapping(value = "/branches")
    public ResponseEntity<Object> createBranch(@RequestBody Branch bank) {
 
        logger.info("(Service Side) Creating Bank: " + bank.getBranchId());
 
        Branch savedBank = branchRepository.save(bank);

    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBank.getBranchId()).toUri();
    	logger.info("(Service Side) Add Bank: " + bank.getBranchId());

    	return ResponseEntity.created(location).build();
    }
 
    @PutMapping("/branches/{id}")
    public ResponseEntity<Object> updateBranch(@RequestBody Branch branch, @PathVariable("id") long branchId) {
 
    	Optional<Branch> bankOptional = branchRepository.findById(branchId);

    	if (!bankOptional.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	branch.setBranchId(branchId);
    	
    	branchRepository.save(branch);
    	logger.info("(Service Side) Editing Bank: " + branch.getBranchId());

    	return ResponseEntity.noContent().build();
 
    }
 
    @DeleteMapping("/branches/{id}")
    public void deleteBranch(@PathVariable("id") Long branchId) {
 
        logger.info("(Service Side) Deleting Bank: " + branchId);
        branchRepository.deleteById(branchId);
    }
 
}

