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

import com.example.demo.entity.Customer;
import com.example.demo.repo.CustomerRepository;
 
@RestController
 
public class CustomerController {
 
   private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository customerRepository;
	
//    @RequestMapping("/")
//    @ResponseBody
//    public String welcome() {
//        return "Welcome to CustomerController Rest";
//    }
// 
    @GetMapping(value = "/customers")
    @ResponseBody
    public List<Customer> getCustomers() {
    	List<Customer> list = customerRepository.findAll();
    	
        return list;
    }
 
    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        
    	logger.info("CustomerController->getCustomer by id:{} has started",customerId);
    	
    	//retreive by cache
        //Optional<Customer> customer = customerRepository.getCustomerById(customerId);
    	
    	Optional<Customer> customer = customerRepository.findById(customerId);

    	if (!customer.isPresent())
    		throw new CustomerNotFoundException("id-" + customerId);

    	return customer.get();
    }
 
    @PostMapping(value = "/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
 
        logger.info("(Service Side) Creating Customer: " + customer.getCustomerId());
 
        Customer savedCustomer = customerRepository.save(customer);

    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCustomer.getCustomerId()).toUri();
    	logger.info("(Service Side) Add Customer: " + customer.getCustomerId());

    	return ResponseEntity.created(location).build();
    }
 
    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable("id") long customerId) {
 
    	Optional<Customer> customerOptional = customerRepository.findById(customerId);

    	if (!customerOptional.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	customer.setCustomerId(customerId);
    	
    	customerRepository.save(customer);
    	logger.info("(Service Side) Editing Customer: " + customer.getCustomerId());

    	return ResponseEntity.noContent().build();
 
    }
 
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Long customerId) {
 
        logger.info("(Service Side) Deleting Customer: " + customerId);
        customerRepository.deleteById(customerId);
    }
 
}

