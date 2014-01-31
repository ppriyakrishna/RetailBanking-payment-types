package com.retailBanking.payments.dao;

import java.util.List;

import com.retailBanking.payments.domain.Bank;

public interface BankDao {
	
	public List<Bank> getAllBankDetails();
	public Bank getBankDetails(int bank_Id);
	public int findBankId(String bank_Name);
	public void insert(Bank bank);

}
