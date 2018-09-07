package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;

@Repository
@Transactional(readOnly = true)
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryCustomImpl.class);

    @PersistenceContext
    EntityManager entityManager;

	@Override
    @Cacheable("Customers")
	public Optional<Customer> getCustomerById(Long customerId) {
		
		logger.info("getCustomerById go to dataBase for id:"+customerId);

		Query query = entityManager.createNativeQuery("SELECT * FROM CUSTOMER  " +
                "WHERE customer_Id = ?", Customer.class);
        query.setParameter(1, customerId );
        List<Customer> customers = query.getResultList();
        
       	return Optional.of(customers.get(0));
        
	}
	
	private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
