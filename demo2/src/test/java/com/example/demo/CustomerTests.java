package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.CustomerController;
import com.example.demo.controller.CustomerNotFoundException;
import com.example.demo.dto.GenderEnum;
import com.example.demo.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTests {

	@Autowired
	private CustomerController customerController;

   private static final Logger logger = LoggerFactory.getLogger(CustomerTests.class);
	
	//@Test
	public void contextLoads() {
	}

	@Test(expected = CustomerNotFoundException.class)
	public void testCreateUpdateDeleteCustomerById() throws CustomerNotFoundException {

		final Long  CUSTOMER_ID_CREATED = 1L;
		Customer customer;
		try {
			//customer = customerController.getCustomer(1L);
			Customer customerNew = new Customer(10L, "first", "last", GenderEnum.MALE);

			customerController.createCustomer(customerNew);
			customer = customerController.getCustomer(CUSTOMER_ID_CREATED);
			assertNotNull(customer);

			customer.setFirstName("FIRST");
			customerController.updateCustomer(customer, CUSTOMER_ID_CREATED);

			customer = customerController.getCustomer(CUSTOMER_ID_CREATED);
			assertEquals(customer.getFirstName(), "FIRST");
			

			customerController.deleteCustomer(CUSTOMER_ID_CREATED);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
			customer = customerController.getCustomer(CUSTOMER_ID_CREATED);
			assertNull(customer);
			logger.info("finish testCreateUpdateDeleteCustomerById ");


	}

}
