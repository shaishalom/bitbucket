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

import com.example.demo.entity.Branch;

@Repository
@Transactional(readOnly = true)
public class BranchRepositoryCustomImpl implements BranchRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(BranchRepositoryCustomImpl.class);

    @PersistenceContext
    EntityManager entityManager;

	@Override
    @Cacheable("Branches")
	public Optional<Branch> getBranchById(Long branchId) {
		
		logger.info("getBranchBranchById go to dataBase for id:"+branchId);

		Query query = entityManager.createNativeQuery("SELECT * FROM BRANCH  " +
                "WHERE branch_Id = ?", Branch.class);
        query.setParameter(1, branchId );
        @SuppressWarnings("unchecked")
        List<Branch> branches = query.getResultList();
       	return Optional.of(branches.get(0));
        
	}
	


}
