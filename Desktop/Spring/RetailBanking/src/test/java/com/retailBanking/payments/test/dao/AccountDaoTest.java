package com.retailBanking.payments.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.retailBanking.payments.dao.AccountDao;
import com.retailBanking.payments.domain.Account;
import com.retailBanking.payments.exceptions.InsufficentFundsException;

@ContextConfiguration("classpath:service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AccountDaoTest {

	private Logger logger=Logger.getLogger(AccountDaoTest.class);

	@Autowired
	private AccountDao accountDAO;
	Account account1 ;
	int user_Id=35;

	@Before
	public void setUp(){
		account1 = new Account();
		account1.setAccount_Name("Nancy");
		account1.setAmount(5000.00);
		account1.setBranch_Name("fremontBrnch");
		
	}

	/**
	 * Testing whether the insert in Account table  
	 * working properly or not
	 */
	@Test
	public void testInsertAccount() {
		accountDAO.insertAccount(account1,user_Id);
	    long new_Accountid = account1.getAccount_Id();

		assertTrue(new_Accountid > 0);
		Account account = accountDAO.getAccountDetails(new_Accountid);
		assertEquals(account.getAccount_Id(),new_Accountid);
	}


	/**
	 * Testing whether the withDrawl in Account table
	 * working properly or not
	 */
	@Test
	public void testWithDrawl() {		

		int rows=0;
		accountDAO.insertAccount(account1,user_Id);
		 long new_Accountid = account1.getAccount_Id();
		   
		try {	

			rows=accountDAO.withDrawl(new_Accountid,1500);	

		} catch (InsufficentFundsException ex) {
			/*  Account update have been rolled back because we
			 * have a RunTime exception.
			 */
			logger.debug("Update Failed   The exception is: " + ex);
		}

		assertEquals(rows,1);
	}
	
	/**
	 * Testing the withDrawl  when the amount to with draw is more than
	 * actual amount in the account .
	 * if it so than InSufficentFundsException has to be raised 
	 */
	@Test
	public void testRollBackWithDrawl() {		

		int rows=0;
		accountDAO.insertAccount(account1,user_Id);
		 long new_Accountid = account1.getAccount_Id();
		   
		try {	

			rows=accountDAO.withDrawl(new_Accountid,5500);	
			fail();
		} catch (InsufficentFundsException ex) {
			/*  Account update have been rolled back because we
			 * have a RunTime exception.
			 */
			logger.debug("Update Failed   The exception is: " + ex);
		}

		
	}


	/**
	 * Testing the delete statement in the Account table.
	 */
	@Test
	public void testAccountDelete(){
		accountDAO.insertAccount(account1,user_Id);
		 long new_Accountid = account1.getAccount_Id();
		int rows=accountDAO.deleteAccount(new_Accountid);

		assertEquals(rows, 1);
	}
	
	



}
