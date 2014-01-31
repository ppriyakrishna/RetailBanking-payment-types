package com.retailBanking.payments.dao;


import java.util.List;

import com.retailBanking.payments.domain.Account;

public interface AccountDao {
	
	public void insertAccount(Account account,int user_Id);
	public Account getAccountDetails(long accountId);
	public Account getAccountFromUser(int user_id);
	public Account getAccountDetails(String account_Name);
	public List<Account> getAllAccountDetails();
	public List<Account> getAllAccountDetails(int user_Id);
	public int withDrawl(long account_id,double amt);
	public int deposit(long account_id,double amt);
	public int deleteAccount(long accountId);
		
	

}
