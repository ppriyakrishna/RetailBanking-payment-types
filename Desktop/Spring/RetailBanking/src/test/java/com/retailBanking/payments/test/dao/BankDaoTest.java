package com.retailBanking.payments.test.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.retailBanking.payments.dao.BankDao;
import com.retailBanking.payments.domain.Bank;

@ContextConfiguration("classpath:service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BankDaoTest {
	
	private Logger logger=Logger.getLogger(BankDaoTest.class);
	
	@Autowired
	private BankDao bankDao;
	

	@Test
	public void testBank() {
		int bank_Id=1;
		
		Bank bank=bankDao.getBankDetails(bank_Id);
		logger.debug("Bank:"+bank);
		
		
	}

}
