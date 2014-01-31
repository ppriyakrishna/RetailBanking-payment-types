package com.retailBanking.payments.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.retailBanking.payments.domain.Bank;

public class BankRowMapper  implements RowMapper<Bank> {

	public Bank mapRow(ResultSet resultSet, int row) throws SQLException {
		int bank_Id;
		String bank_Name;
		String country_Name;
		String currency;
		
		Bank bank=new Bank();
		
		bank_Id=resultSet.getInt("bank_Id");
		bank_Name = resultSet.getString("bank_Name");
		country_Name=resultSet.getString("country_Name");
		currency=resultSet.getString("currency");
		
		bank.setBank_Id(bank_Id);
		bank.setBank_Name(bank_Name);
		bank.setCountry_Name(country_Name);
		bank.setCurrency(currency);
		
		return bank;
	} 

}
