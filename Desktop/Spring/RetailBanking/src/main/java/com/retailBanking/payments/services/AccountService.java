package com.retailBanking.payments.services;

import java.util.List;

import com.retailBanking.payments.domain.Account;

public interface AccountService {
	
	public void insertAccount(Account account,int user_Id);
	public Account getAccountDetails(long accountId);
	public Account getAccountDetails(String account_Name);
	public List<Account> getAllAccountDetails();
	public int withDrawl(long account_id,double amt);
	public int deposit(long account_id,double amt);
	public int deleteAccount(long accountId);
	public Account getAccountFromUser(int user_id);
	public List<Account> getAllAccountDetailsFromUser(int user_Id);
	public List<Long> getAllAccount_Ids(int user_Id);
	

}
