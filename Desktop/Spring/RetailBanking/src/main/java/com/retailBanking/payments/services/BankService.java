package com.retailBanking.payments.services;

import java.util.List;

import com.retailBanking.payments.domain.Bank;

public interface BankService {
	
	public List<Bank> getAllBankDetails();
	public Bank getBankDetails(int bank_Id);
	public int findBankId(String bank_Name);
	public void insert(Bank bank);
	public List<String> getAllBankCurrencies();
	public List<String> getAllBankNames();
	public List<String> getAllCountryNames();
	

}
