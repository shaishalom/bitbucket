package com.example.demo.repo;



	 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.GenderEnum;
import com.example.demo.entity.Customer;
	 
	@Repository
	public class CustomerDAO {
	 
	    private static final Map<Long, Customer> customerMap = new HashMap<Long, Customer>();
	 
	    static {
	        initEmps();
	    }
	 
	    private static void initEmps() {
	    	Customer c1 = new Customer(1L, "Shai", "Shalom", GenderEnum.MALE);
	    	Customer c2 = new Customer(2L, "Carmel", "Shalom",GenderEnum.FEMALE);
	    	Customer c3 = new Customer(3L, "Kobi", "bachar",GenderEnum.MALE);
	 
	        customerMap.put(c1.getCustomerId(), c1);
	        customerMap.put(c2.getCustomerId(), c2);
	        customerMap.put(c3.getCustomerId(), c3);
	    }
	 
	    public Customer getCustomer(Long customerId) {
	        return customerMap.get(customerId);
	    }
	 
	    public Customer addCustomer(Customer cust) {
	        customerMap.put(cust.getCustomerId(), cust);
	        return cust;
	    }
	 
	    public Customer updateCustomer(Customer cust) {
	        customerMap.put(cust.getCustomerId(), cust);
	        return cust;
	    }
	 
	    public void deleteCustomer(Long customerId) {
	        customerMap.remove(customerId);
	    }
	 
	    public List<Customer> getAllCustomers() {
	        Collection<Customer> c = customerMap.values();
	        List<Customer> list = new ArrayList<Customer>();
	        list.addAll(c);
	        return list;
	    }
}
