package com.retailBanking.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailBanking.payments.domain.Bank;
import com.retailBanking.payments.domain.FundsTransfer;
import com.retailBanking.payments.exceptions.CurrencyIndifferenceException;

@Service("fundsTransferService")
public class FundsTransferServiceImpl implements FundsTransferService{

	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private BankService bankService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private PaymentTypeService paymentTypeService;


	public boolean validate(FundsTransfer fundsTransfer){

		Bank fromBank=fundsTransfer.getFromBank();
		Bank toBank=fundsTransfer.getToBank();

		if(fromBank.equals(toBank)){
			return true;
		}
		return false;
	}




	public void directFundsTransfer(FundsTransfer fundsTransfer) {

		if(validate(fundsTransfer)){
			transactionService.transferAmount(fundsTransfer);
		}else{
			throw new CurrencyIndifferenceException("DFTRULE");
		}

	}


	public void crossBorderFundsTransfer(FundsTransfer fundsTransfer) {
		if(!validate(fundsTransfer)){
			transactionService.transferAmount(fundsTransfer);
		}


	}




	public void fundsTransfer(FundsTransfer fundsTransfer) {
		String payTypeCode=fundsTransfer.getPaymentTypeCode();

		if(payTypeCode.equals("DFT")){
			directFundsTransfer(fundsTransfer);
		}else if(payTypeCode.equals("CBFT")){
			crossBorderFundsTransfer(fundsTransfer);
		}

	}


}
