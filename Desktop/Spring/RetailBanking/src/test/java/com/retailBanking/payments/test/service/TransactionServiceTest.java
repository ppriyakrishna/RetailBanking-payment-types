package com.retailBanking.payments.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.retailBanking.payments.dao.AccountDao;
import com.retailBanking.payments.domain.Account;
import com.retailBanking.payments.domain.FundsTransfer;
import com.retailBanking.payments.services.TransactionService;

@ContextConfiguration("classpath:test_root_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest {

	

	@Autowired
	private TransactionService transactionService;	
	@Autowired
	private AccountDao accountDao;
	
	final double DELTA=1e-15;
	
	
	/**
	 * Transfering the amount from one account say 'A' to 
	 * another account say 'B' than in  A account amount has to be
	 * reduced and in B amount has to increase.
	 */
	@Test
	public void testTranfer() {	
		
		Account account1 = new Account();
		account1.setAccount_Name("Nancy");
		account1.setAmount(5000.00);
		account1.setBranch_Name("fremontBrnch");
		
		
		Account account2 = new Account();
		account2.setAccount_Name("Emma");
		account2.setAmount(5000.00);
		account2.setBranch_Name("fremontBrnch");
		
		
		accountDao.insertAccount(account1,35);
		long account_id1=account1.getAccount_Id();
		
		accountDao.insertAccount(account2,35);	
		
		
		double intialAmount=account1.getAmount();
		FundsTransfer fundsTranfer=new FundsTransfer();
		
		fundsTranfer.setFromAccount(account1);
		fundsTranfer.setToAccount(account2);
		fundsTranfer.setTransferAmt(500);
		
		transactionService.transferAmount(fundsTranfer);
		account1=accountDao.getAccountDetails(account_id1);
		double afterAmount=account1.getAmount();

		assertEquals(afterAmount,intialAmount-500,DELTA);		
	}

}
