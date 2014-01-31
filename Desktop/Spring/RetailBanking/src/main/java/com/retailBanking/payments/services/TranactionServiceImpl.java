package com.retailBanking.payments.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retailBanking.payments.dao.AccountDao;
import com.retailBanking.payments.domain.FundsTransfer;


@Service("transactionService")
@Transactional(rollbackForClassName="InsufficentFundsException")
public class TranactionServiceImpl implements TransactionService{
	
	private Logger logger=Logger.getLogger(TranactionServiceImpl.class);
	
	@Autowired
	public AccountDao accountDao;
	
	
	/**
	 * Transfer the amount from one account by with drawing to 
	 * another account by depositing 
	 * 	
	 */
	public void transferAmount(FundsTransfer fundsTransfer) {
		double amount=fundsTransfer.getTransferAmt();
		
		accountDao.withDrawl(fundsTransfer.getFromAccount_Id(),amount );	
		accountDao.deposit(fundsTransfer.getToAccount_Id(), amount);			
		logger.debug("Transfer is done");
	}

}
