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

@Repository
@Transactional(readOnly = true)
public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryCustomImpl.class);

    @PersistenceContext
    EntityManager entityManager;

	@Override
    @Cacheable("Accounts")
	public Optional<Account> getAccountById(Long accountId) {
		
		logger.info("getAccountById go to dataBase for id:"+accountId);
		Query query = entityManager.createNativeQuery("SELECT * FROM ACCOUNT  " +
                "WHERE account_Id = ?", Account.class);
        query.setParameter(1, accountId );
        @SuppressWarnings("unchecked")
		List<Account> accounts = query.getResultList();
       	return Optional.of(accounts.get(0));
        
	}
	

}
