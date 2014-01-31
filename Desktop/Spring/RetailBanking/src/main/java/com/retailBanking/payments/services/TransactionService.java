package com.retailBanking.payments.services;

import com.retailBanking.payments.domain.FundsTransfer;



public interface TransactionService {	
	public void transferAmount(FundsTransfer fundsTransfer);
}
