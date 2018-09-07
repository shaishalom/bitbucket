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

import com.example.demo.controller.AccountController;
import com.example.demo.controller.AccountNotFoundException;
import com.example.demo.controller.BankController;
import com.example.demo.controller.BankNotFoundException;
import com.example.demo.controller.BranchController;
import com.example.demo.controller.BranchNotFoundException;
import com.example.demo.entity.Account;
import com.example.demo.entity.Bank;
import com.example.demo.entity.Branch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BranchTests {

	@Autowired
	private BranchController branchController;

	@Autowired
 	private BankController bankController;

   private static final Logger logger = LoggerFactory.getLogger(BranchTests.class);
	

	@Test(expected = BranchNotFoundException.class)
	public void testCreateUpdateDeleteBranchById() throws BranchNotFoundException {

		final Long  BRANCH_ID_CREATED = 14L;
		Branch branch;
		try {
			Bank bank  = bankController.getBank(1L);
			//account = accountController.getAccount(1L);
			Branch branchNew = new Branch(BRANCH_ID_CREATED,"ADDR SHAI 1",bank);
			
			branchController.createBranch(branchNew);
			branch = branchController.getBranch(BRANCH_ID_CREATED);
			assertNotNull(branch);

			branch.setAddress("ADDR SHAI 2");
			branchController.updateBranch(branch, BRANCH_ID_CREATED);
			
			branch = branchController.getBranch(BRANCH_ID_CREATED);
			assertEquals(branch.getAddress(), "ADDR SHAI 2");
			

			branchController.deleteBranch(BRANCH_ID_CREATED);
		} catch (BranchNotFoundException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		} catch (BankNotFoundException e) {
			fail(e.getMessage());
		}
		
			branch = branchController.getBranch(BRANCH_ID_CREATED);
			assertNull(branch);


	}

}
