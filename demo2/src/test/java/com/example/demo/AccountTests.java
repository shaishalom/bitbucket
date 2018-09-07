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
import com.example.demo.controller.BranchController;
import com.example.demo.controller.BranchNotFoundException;
import com.example.demo.entity.Account;
import com.example.demo.entity.Branch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTests {

	@Autowired
	private AccountController accountController;

	@Autowired
 	private BranchController branchController;

   private static final Logger logger = LoggerFactory.getLogger(AccountTests.class);
	

	@Test(expected = AccountNotFoundException.class)
	public void testCreateUpdateDeleteAccountById() throws AccountNotFoundException {

		final Long  ACCOUNT_ID_CREATED = 14L;
		Account account;
		try {
			Branch branch  = branchController.getBranch(1L);
			//account = accountController.getAccount(1L);
			Account accountNew = new Account(ACCOUNT_ID_CREATED,777D,branch);
			
			accountController.createAccount(accountNew);
			account = accountController.getAccount(ACCOUNT_ID_CREATED);
			assertNotNull(account);

			account.setBalance(555D);
			accountController.updateAccount(account, ACCOUNT_ID_CREATED);
			
			account = accountController.getAccount(ACCOUNT_ID_CREATED);
			assertEquals(account.getBalance(), new Double(555D));
			

			accountController.deleteAccount(ACCOUNT_ID_CREATED);
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		} catch (BranchNotFoundException e) {
			fail(e.getMessage());
		}
		
			account = accountController.getAccount(ACCOUNT_ID_CREATED);
			assertNull(account);


	}

}
