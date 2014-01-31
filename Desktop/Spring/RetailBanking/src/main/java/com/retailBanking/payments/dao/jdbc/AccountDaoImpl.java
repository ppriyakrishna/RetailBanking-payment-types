package com.retailBanking.payments.dao.jdbc;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.retailBanking.payments.dao.AccountDao;
import com.retailBanking.payments.dao.BranchDao;
import com.retailBanking.payments.domain.Account;
import com.retailBanking.payments.exceptions.InsufficentFundsException;


@Repository("accountDao")
public class AccountDaoImpl implements AccountDao{

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	@Autowired
	@Qualifier("branchDao")
	private BranchDao branchDao;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private AccountRowMapper accountRowMapper;


	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		accountRowMapper = new AccountRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		.withTableName("Account")
		.usingGeneratedKeyColumns("account_Id")
		.usingColumns("account_Name", "amount","currency","branch_Id","branch_Name","user_Id");
	}



	public void insertAccount(Account account,int user_Id) {

		long branch_Id=branchDao.findBranchId(account.getBranch_Name());

		account.setBranch_Id(branch_Id);
		account.setUser_Id(user_Id);

		SqlParameterSource params = new BeanPropertySqlParameterSource(account);

		Number newId = jdbcInsert.executeAndReturnKey(params);
		account.setAccount_Id(newId.longValue());

	}


	public Account getAccountDetails(long account_Id) {
		String sql = "SELECT * FROM Account WHERE account_Id = :account_Id";
		MapSqlParameterSource params = new MapSqlParameterSource("account_Id", account_Id);
		try{
			return dbTemplate.queryForObject(sql, params, accountRowMapper);
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}


	}


	public List<Account> getAllAccountDetails() {
		String sql = "SELECT * FROM Account";
		List<Account> accountList = jdbcTemplate.query(sql, accountRowMapper);
		return accountList;
	}




	public int deleteAccount(long account_Id) {
		Account account=getAccountDetails(account_Id);
		MapSqlParameterSource params = new MapSqlParameterSource("account_Id", account_Id);
		String sql = "delete  from  Account  WHERE account_Id = :account_Id";
		int rowsAffected=0;

		if(account!=null){
			rowsAffected = dbTemplate.update(sql,params);	
		}

		return rowsAffected;
	}






	public int  update(long account_Id,double newAmt,int choice){
		Account account=getAccountDetails(account_Id);
		double amount=account.getAmount();
		MapSqlParameterSource params;
		String sql = "update   Account set amount=:amount WHERE account_Id = :account_Id";
		int rowsAffected=0;


		if(choice==1){
			amount-=newAmt;
		}else{
			amount+=newAmt;
		}
		if(amount>0){
			params = new MapSqlParameterSource("account_Id", account_Id);
			params.addValue("amount", amount);
			rowsAffected = dbTemplate.update(sql, params);

		}else{
			throw new InsufficentFundsException("NoFundsAvailable");
		}


		return rowsAffected;
	}


	/**
	 * Withdraw from the given account ,if amount to
	 * with draw is more than actual amount  
	 */
	

	public int withDrawl(long account_id, double amt) {
		int rows=update(account_id,amt,1);
		return rows;
	}


	
	public int deposit(long account_id, double amt) {
		int rows=update(account_id,amt,2);
		return rows;
	}



	public Account getAccountDetails(String account_Name) {
		String sql = "SELECT * FROM Account WHERE account_Name = :account_Name";

		MapSqlParameterSource params = new MapSqlParameterSource("account_Name", account_Name);
		try{
			return  dbTemplate.queryForObject(sql, params, accountRowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}


	}



	public Account getAccountFromUser(int user_Id) {
		String sql = "SELECT * FROM Account WHERE user_Id = :user_Id";
		MapSqlParameterSource params = new MapSqlParameterSource("user_Id", user_Id);
		Account account = dbTemplate.queryForObject(sql, params, accountRowMapper);

		return account;
	}



	public List<Account> getAllAccountDetails(int user_Id) {
		String sql = "SELECT * FROM Account where  user_Id= :user_Id";	
		MapSqlParameterSource params = new MapSqlParameterSource("user_Id", user_Id);
		List<Account> accountList = dbTemplate.query(sql,params,accountRowMapper);
		return accountList;
	}




}
