package com.retailBanking.payments.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailBanking.payments.dao.AccountDao;
import com.retailBanking.payments.domain.Account;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDao accountDao;

	
	public void insertAccount(Account account,int user_Id) {
		accountDao.insertAccount(account,user_Id);
		
	}

	
	public Account getAccountDetails(long accountId) {
		return accountDao.getAccountDetails(accountId);
	}

	
	public List<Account> getAllAccountDetails() {
		return accountDao.getAllAccountDetails();
	}

	
	public int withDrawl(long account_id, double amt) {
		return accountDao.withDrawl(account_id, amt);
	}

	
	public int deposit(long account_id, double amt) {
		return accountDao.deposit(account_id, amt);
	}

	
	public int deleteAccount(long accountId) {
		return accountDao.deleteAccount(accountId);
	}

	
	public Account getAccountDetails(String account_Name) {
		return accountDao.getAccountDetails(account_Name);
	}


	public Account getAccountFromUser(int user_id) {
		return accountDao.getAccountFromUser(user_id);
	}


	public List<Account> getAllAccountDetailsFromUser(int user_Id) {
		return accountDao.getAllAccountDetails(user_Id);
	}


	public List<Long> getAllAccount_Ids(int user_Id) {
		List<Account> accountList=accountDao.getAllAccountDetails(user_Id);;
		List<Long>accountIdList=new ArrayList<Long>();
		
		for(Account account:accountList){
			accountIdList.add(account.getAccount_Id());
		}
		
		return accountIdList;
	}

}
