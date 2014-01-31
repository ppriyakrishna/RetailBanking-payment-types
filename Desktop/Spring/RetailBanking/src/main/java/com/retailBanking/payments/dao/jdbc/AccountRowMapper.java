package com.retailBanking.payments.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.retailBanking.payments.domain.Account;

public class AccountRowMapper implements RowMapper<Account> {

	public Account mapRow(ResultSet resultSet, int row) throws SQLException {
		long account_Id;
		String account_Name;
		long branch_Id;
		double amount;
		String branch_Name;
		String currency;
		int user_Id;
		
		Account account=new Account();
		
		account_Id=resultSet.getLong("account_Id");
		account_Name = resultSet.getString("account_Name");
		branch_Id=resultSet.getLong("branch_Id");
		amount = resultSet.getDouble("amount");
		branch_Name=resultSet.getString("branch_Name");
		currency=resultSet.getString("currency");
		user_Id=resultSet.getInt("user_Id");
		
		account.setAccount_Id(account_Id);
		account.setAccount_Name(account_Name);
		account.setAmount(amount);
		account.setBranch_Id(branch_Id);
		account.setBranch_Name(branch_Name);
		account.setCurrency(currency);
		account.setUser_Id(user_Id);
		
		return account;
	} 

}
