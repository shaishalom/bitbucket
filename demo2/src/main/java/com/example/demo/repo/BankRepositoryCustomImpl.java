package com.example.demo.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Bank;

@Repository
@Transactional(readOnly = true)
public class BankRepositoryCustomImpl implements BankRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(BankRepositoryCustomImpl.class);

    @PersistenceContext
    EntityManager entityManager;

	@Override
    @Cacheable("Banks")
	public Optional<Bank> getBankById(Long bankId){
		
		logger.info("getBankById go to dataBase for id:"+bankId);

		Query query = entityManager.createNativeQuery("SELECT * FROM BANK  " +
                "WHERE bank_Id = ?", Bank.class);
        query.setParameter(1, bankId );
        List<Bank> banks = query.getResultList();
       	return Optional.of(banks.get(0));
        
	}
	


}
