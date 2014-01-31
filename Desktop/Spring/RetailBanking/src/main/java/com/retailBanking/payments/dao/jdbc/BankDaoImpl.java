package com.retailBanking.payments.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.retailBanking.payments.dao.BankDao;
import com.retailBanking.payments.domain.Bank;

@Repository("bankDao")
public class BankDaoImpl implements BankDao{
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private BankRowMapper bankRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		bankRowMapper = new BankRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("bankdetails")
		                 .usingGeneratedKeyColumns("bank_Id")
		                 .usingColumns("bank_Name","country_Name","currency");
	}
	
	
	public void insert(Bank bank) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(bank);
	
		Number newId = jdbcInsert.executeAndReturnKey(params);
		bank.setBank_Id(newId.intValue());
		
	}

	
	/**
	 * Get all the Bank details from the 
	 * Bank table
	 */
	
	public List<Bank> getAllBankDetails() {
		String sql = "SELECT * FROM BankDetails";
		List<Bank> bankList = jdbcTemplate.query(sql, bankRowMapper);
		return bankList;
	}

	/**
	 * Get all the Bank details  from the bank_id
	 */
	
	public Bank getBankDetails(int bank_Id) {
		String sql = "SELECT * FROM BankDetails WHERE bank_Id = :bank_Id";
		MapSqlParameterSource params = new MapSqlParameterSource("bank_Id", bank_Id);
		Bank bank = dbTemplate.queryForObject(sql, params, bankRowMapper);

		return bank;
	}




	public int findBankId(String bank_Name) {
		String sql = "SELECT bank_Id FROM BankDetails WHERE bank_Name = :bank_Name";
		MapSqlParameterSource params = new MapSqlParameterSource("bank_Name", bank_Name);
		int bank_Id = dbTemplate.queryForInt(sql, params);

		return bank_Id;
	}


}
