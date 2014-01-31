package com.retailBanking.payments.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailBanking.payments.dao.BankDao;
import com.retailBanking.payments.domain.Bank;

@Service("bankService")
public class BankServiceImpl implements BankService{

	@Autowired
	private BankDao bankDao;


	public List<Bank> getAllBankDetails() {
		return bankDao.getAllBankDetails();
	}

	public List<String> getAllBankCurrencies(){
		List<Bank> bankList=getAllBankDetails();
		List<String> currencyList=new ArrayList<String>();

		for(Bank bank:bankList){
			String currency=bank.getCurrency();
			if(!currencyList.contains(currency)){
				currencyList.add(currency);
			}
		}

		return currencyList;
	}

	public List<String> getAllBankNames(){
		List<Bank> bankList=getAllBankDetails();
		List<String> bankNameList=new ArrayList<String>();

		for(Bank bank:bankList){
			String bankName=bank.getBank_Name();
			if(!bankNameList.contains(bankName)){
				bankNameList.add(bankName);
			}
		}

		return bankNameList;

	}

	public List<String> getAllCountryNames(){
		List<Bank> bankList=getAllBankDetails();
		List<String> countrNameList=new ArrayList<String>();

		for(Bank bank:bankList){
			String countryName=bank.getCountry_Name();
			if(!countrNameList.contains(countryName)){
				countrNameList.add(countryName);
			}
		}

		return countrNameList;


	}


	public Bank getBankDetails(int bank_Id) {
		return bankDao.getBankDetails(bank_Id);
	}


	public int findBankId(String bank_Name) {
		return bankDao.findBankId(bank_Name);
	}


	public void insert(Bank bank) {
		bankDao.insert(bank);

	}


}
